package main

import "fmt"


type OP int

const (
	ADD OP = 0
	INC OP = 1
	STORE OP = 2
	DUAL OP = 3
	SNGL OP = 4
)

type Msg struct {
	op OP
	p1 int
	p2 int
	ret chan int
}

type State struct {
	n int;
}

func loop1(ch chan Msg, state State) {
	msg := <-ch;
	
	if (msg.op == STORE) {
		go loop1(ch, State{state.n + msg.p1})
	} else if (msg.op == INC) {
		msg.ret <- state.n + msg.p1;
		go loop1(ch, state);
	} else if (msg.op == DUAL) {
		go loop2(ch);
	} else {
		go loop1(ch, state);
	}
}

func loop2(ch chan Msg) {
	msg := <-ch;

	if (msg.op == ADD) {
		msg.ret <- msg.p1 + msg.p2;
		go loop2(ch);
	} else if (msg.op == SNGL) {
		go loop1(ch, State{n : 0});
	} else {
		go loop2(ch);
	}

}

/* add functions here */

func main() {
	// simple test case ,write more
	input := make(chan Msg)
	go loop1(input, State{n: 0})
	res := make(chan int)
	input <- Msg{STORE, 2, 0, res}
	input <- Msg{INC, 5, 0, res}
	fmt.Println(<-res) //should print 7
	input <- Msg{DUAL, 0, 0, res}
	input <- Msg{ADD, 2, 5, res}
	fmt.Println(<-res);
	input <- Msg{SNGL, 0, 0, res}
	input <- Msg{STORE, 2, 0, res}
	input <- Msg{STORE, 2, 0, res}
	input <- Msg{STORE, 10, 0, res}
	input <- Msg{STORE, -3, 0, res}
	input <- Msg{INC, 1, 0, res}
	fmt.Println(<-res);

}
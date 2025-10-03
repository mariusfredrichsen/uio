package main

import (
	"fmt"
)

func main() {
	ch := make(chan float64)
	go receivef(ch)
	sendf(ch)
}

func sendf(ch chan<- float64) {
	ch <- 5
}

func receivef(ch <-chan float64) {
	num := <-ch
	fmt.Println(num)
}
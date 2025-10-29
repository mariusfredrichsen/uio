package main

import (
	"fmt"
	"sync"
)


type Mapper interface {
/* fill signatures here */
}

type Reducer interface {
/* fill signatures here */
}

func Map(input <-chan int, output1 chan<- int, output2 chan<- int, mapper Mapper, wg *sync.WaitGroup /* more channels here, if required */) {
  /* add map functionality here */
  defer wg.Done();

  for {
    num, ok := <-input;
    if (!ok) {break;}

    if (num % 2 == 0) {
      output1 <-(num*num);
    } else {
      output2 <-(num*num);
    }
  }
}

func Reduce(input <-chan int, output chan<- int, reducer Reducer, init int /* more channels here, if required */){
  /* add reduce functionality here */
  
  for {
    num, ok := <-input
    if (!ok) {output<- init; break;}
    init += num
  }
  
  
}


/* add code for shutdown functionality here, if required */


type EvenOddMapper struct {}
type EvenOddReducer struct {}

/* implement your interfaces here */


func main() {
	input := make(chan int)
	inter1 := make(chan int) //even
	inter2 := make(chan int) //odd
  output := make(chan int) // sum

  var wg0 sync.WaitGroup;
  wg0.Add(2);

/* channels and processes for shutdown here */

    go Map(input, inter1, inter2, EvenOddMapper{}, &wg0 /* parameters here, if needed */)
    go Map(input, inter1, inter2, EvenOddMapper{}, &wg0/* parameters here, if needed */)
    go Reduce(inter1, output, EvenOddReducer{}, 0)
    go Reduce(inter2, output, EvenOddReducer{}, 0)
    
    n := 1000;
    sum := 0;
    for i := 1; i <= n; i++ {
      input <- i;
      sum += i*i;
    }

    close(input)
    wg0.Wait()
    close(inter1); 
    close(inter2);    

/* initiate shutdown here */
    res := <-output + <-output
    fmt.Println(res) //should be 55 for example
    fmt.Println(sum)
    close(output)
}

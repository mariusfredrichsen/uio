package main

import (
	"fmt"
)

type Pair struct { X, Y float64 }
type Triple struct {
	Pair
	Z    float64
}

func main() {
	pair := Pair{1,2}
	triple := Triple{pair,3}

	fmt.Println(pair)
	fmt.Println(triple)
}



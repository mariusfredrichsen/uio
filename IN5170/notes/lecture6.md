# Concurrency in Go


### Channels
- **make** to make a channel
- **<-** to define what direction the message is going
    - **ch <-chan**, receive from this channel
    - **ch chan<-**, send to this channel
- **close(ch)**, closes the channel
    - closing a channel sends default values to the channels that are receiving
- **defer**, it waits to execture the code in the same line until everything else is done in the function
    - `defer` fmt.Println("World"); fmt.Println("Hello")
    - prints "Hello\nWorld"

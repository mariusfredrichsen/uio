# Convolutional Neural Network (CNN)
- Convolution in Deep learning, sliding window of inner products
- Forward networks is fully connected
    - Tends to mess with the idea that almost only neighbouring pixels are related
- parameter sharing, local pattern detection
- **convolutional operator**
    - only connect the neighbouring neurons in the layers of a network
    - **convolutional kernel**, a sized vector that is multiplied with a window of the same size of the vector
    - take the sum of the convolutional kernel and that is the output/input for the next layer
    - strid is the jump distance for the sliding window
    - padding, extra zero values to enlarge the image for the kernel to have more info?
    - 
- finding horizontal/vertical changes, change of gradient is what activates the neurons
- padding can be done with either zeros, mean value of pixels, nearest pixel value, mirror-reflected indexing
- better to use many layers with simple filters/functions instead of having few layers with complex filters/functions
- we apply a set of local filters
    - multiple small filters rather than few largers filters
- standard padding is k-1 / 2 where k is the size of filter
- receptive field, neurons in deeper layers see the bigger picture
    - bigger stride gives a bigger field 
- **pooling**, reduce the redundecy, shrink the dimension, max pooling or average pooling
    - max pooling picks the max value in a field
    - average pooling just does the same but average


spørsmål:
- hvorfor blir de lengere i lengden? bredden og høyden gir mening
- hvis du jobber med horisontale bilder burde vi jobbe med kvadrater fortsatt?
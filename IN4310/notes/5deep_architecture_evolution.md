# Deep Architecture Evolution
- Visual Geometry Group Architecture
    - conv3-64 => 3x3 filters x 64 of them
    - ReLU
    - Maxpooling
    - the fully connected layers contains the most of the paramters (130million in total)

## Dropout
- temporarily remove a neuron from a network at training time
- add noise so that all neurons are to be updated during training time
- Bernoulli(p) indepentent Bernoulli random variables
- multiply by either 0 or 1 the activation results

## GoogLeNet / Inception
- by google
- auxiliary classifiers at training time only
    - classify in the middle of the layers with a soft max with partially processed data
    - back propegation in the middle of the layers
    - look at the cross entropy at every softmax in the middle the layers
    - calculated with a weighted sum
- inception module
    - using 1x1 convolutions
    - tried to use different sized filters in parallelle and combine them together
        - combining them just means concatinating them, increasing the depth of the next layer
    - naive way is to just do it directly
    - instead use a 1x1 convolutions before using a 3x3 or 5x5 and after a 3x3 max pool
- Inception x3
    - asymmetric convolutions (nx1)
    1x3 then 3x1 gives the same amount of information as 3x3 and is 33% cheaper

## ResNets and Residual Connections
- Residual connections
    - implement skip connections if convolutional layers arent useful
    - make sure taht skip connections are compatible with the layer we are skipping to
    - when dropping under a defined weight we use the skip
    - drawbacks could be missing out on potential good blocks
- ResNets
    - batch normilization after every convolutions
    - dropping most of the fully connected layers and just does convolution layers then avg pooling right before the last fully connected layer (the classification layer)
    - 

## Batch Normilization
1. Normalize activiations of a layer
2. Apply an affine transformation (y = alpha*x+beta)
- the mean and standard deviation is the same for one channel
- use the normalization at training time on the tests or actual use
- batchsizes should be meaningful, 8, 16, 32, sometimes helpful
- group normalization
    - (b, c, h, w), batch index, channel index, height index, width index
    - sum(b[\c][\h][\w])
- layer normalization
    - sum of ALL the channels in that layer

## DenseNets
- use the concatinated of featuremaps from earlies layers
- defines a block, dense blocks bevore we do convolutions
- its a extentions of residual connections
- grows big very fast so we just do it in blocks
- usually uses Densenet-BC

## Finetuning
- use already trained models
- low level filters will be very similar if you use a model that is trained on everything
- drop layers if we skip them
- focus on the deeper layers
- train all layers with big datasets but with small datasets train the top-layers

## ViT
- State of the Art (SoTA)
- Visual Transformers



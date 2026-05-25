# Image Segmentation

## Semantic Segmentation
- Object classes
- Background classes
- classify every pixel in an image, ignore splitting multiple instance of hte same class
- capture global context by encode and downsample then upsample to the original resolution
    - upsampling can be done with nearest neighbour, bilinear, bicubic upsampling techniques
    - unppoling
    - transposed convolution OR deconvolution
    - nearest neighbour just scales the pixels, 2x2 == 3x ==> 6x6, use the convolution layer from the corresposing layer
    - unpooling, store the indicies while max-pooling and save them later for when unpooling and make sure to store positions
    - transposed convolution, learns paramters of kernell K to enlarge the input, stride s and padding p
- U-Net, the layers go in a U shape, pool and minimize the layers to then later unpool and increase the layer sizes, while increasing the layer sized we use the information learned in the corresponding earlier layers, done with copy and crop
- DIlated / Astrous conbolution, the convolution is spread in a grid rather than a bigger box

## Instance Segmentation
- Object classes
- Object instances
- perform object detection
- generate a mask for each object in parallel
- mask r-cnn (based on faster r-cnn)
    - cnn -> region propsal network -> RoI align -> fc layers + mask (conv -> conv -> mask) -> classes and boundary boxes

- main performance metrics
    - mean IoU
    - Mask mAP
- other performance metrics
    - Pixel accuracy,fraction of pixerls correctly classified
    - mean pixel accuracy, pixel accuracy average over classes
    - dice coefficient, 2|A intersected B| / (|A| + |B|)
        - similar to IoU, same as F1 score for binary segmentation (background vs foreground)
- basic loss functions
    - semantic segmentation, calculate cross entropy for each pixel, sum the losses of all pixels, balance for class if required
    - instance segmentation, sum of all the three losses, classification loss, box regression loss, mask loss
    - loss in general is modified based on the problem

## Panoptic Segmentation
- Both semantic and instance segmanation
- terminoligy, thing - an object in foreground, stuff - background like grass, mountains, etc.
- new metric, panoptic quality (PQ)
- different approaches
    - sharing backbone, instance and semantic share the same backbone but nothing else
    - explicit connections, instance and semantic share the backbone and has a connection between them
    - one-shot model, instacne and semantic is together
    - cascade model, semantic is first then the results are put into instance then later combined again
        - backbone -> semantic -> instance -+semantic-> result
- segment anything model (SAM)
    - prompt-based segmentation
    - prompt encoder, image encoder -> lightweight mask decored -> valid mask
    - image encoder is a pretrained vision transformer encoder
    - mask decored is based on transofmrer decored + mask prediction head
    
# Object detection

## object location
- normal softmax for output + 1 for number of objects + 4 for row, col, width and height
- c0 value tells us wether it is a object or not just a backrground (relevant objects)

## Loss function
- eucleadian distance for the bounding box

## detecting multiple objects
- need to increase the width of the output (softmax)
- naive way to detect many objects is to do a sliding window

## R-CNN two stage detectors
- stage 1 - propose ROIs (Regions of interesert)
- stage 2 - fix the boxes and classify
- FAST R-CNN
    - ConvNet on the whole image and extract the features in the results of convNet where the ROI is
- FASTER R-CNN
    - pre-trained cnn
    - feature map
    - region proposal network
    - stage 2

## R-CNN one stage detectors


## overlapping predictions
- non-max suppression, IoU between any two boxes, Intersection over union
- remove all poor predictions first
- greedy


## Focal loss
- cross-entropy * (1-p_t)
- scaling down the cross-entropy
- more focused on what it is uncertein about rather than what it already knows
- 
# Performance estimation and generalisation beyond the development dataset

## Evaluation
### Internal testing
- randomly partition the dataset into train subset and test subset
- **unbiased estimation**, estimate on something that he model has never seen before
    - Don't tweak the model based on the test set (unseen data)
- **k-fold cross-validation**, randomly partition into k folds of equal size
    - for k-fold in k-folds, use k-fold as validation and the rest as training
    - takes a lot more time but is more general since it isn't based on one singular training / validation set
    - can use the multiple models outputted to be used in a multi-model creature
- **bootstrapping**, repeatedly partition the non-test subset
    - sample from non-test set until it is big as the non-test set, 63.2% of the data is used due to duplications
    - train using the sampled data
    - usually used when data is sparse
    - validation data can be smaller when using resampling
- Subset sizes is usually 70% training, 15% validation and 15% testing
    - performance based on the sizes of the dataset is log-scaled
        - if you have 100 images then we need more 900 images to make the model more decent
        - if you have 1000000 images and add 900 more then it doesn't really do anything



### External testing
- data must be representable for the application your models is going to be used for
- be careful for domain shift, training vs real world
- external data is data outside of your dataset fetched from others outside of your "domain"



### Facilitating generalisation
- control the networks capacity, # trainable paramters, dropout, weight decay
- facilitate learning, residual connections, batch normalisation, transfer learning, learning rate schedule, optimisation method
- image-specific normalisation, make different images more similar
- data augmentation, birhgtness, color, contrast, rotation, size, sharpness
- more diverse training data



### Some performance metrics
- pick the performance metrics fit for your application
- **confusion matrix**, c x c (c = number of classes)
    - FP = Type 1 error
    - FN = Type 2 error
- **Accuracy** = (TP + TN) / (TP + TN + FP + FN)
- **top-n accuracy**, how often is the model's top n highest prediction(s) correct
- **Precision**, TP / (TP + FP), Accuracy of positive predictions, quality measure
- **Recall**, TP / (TP + FN), precentage of positive elements found, quanitity measure
- **Average-precision**, 
- **F1-score**, (2 * Precision * Recall) / (Precision + Recall) = (2 * TP) / ((2 * TP) + FP + FN)
- **Sensitivity**, TP / (TP + FN), proportion of actual positives that are positive, same as recall
- **Specificity**, TN / (TN + FP), proportion of actual negatives that are negative predictions, ture negative rate
- **Balanced Accuracy**, (TPR + TNR) / 2

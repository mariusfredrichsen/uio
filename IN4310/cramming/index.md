# IN4310 — Deep Learning for Image Analysis: Cramming Notes

Compact study notes for IN4310. Each file summarises one lecture, distilled from the slides and supplemented with personal notes. Follow the order below for a full sweep, or jump to the topic you need.

## Lectures

1. [Linear Algebra & Probability Review](01_linear_algebra_and_probability.md)
   Vectors, matrices, norms, eigenvalues, gradients/Hessians; basics of probability, expectation, Bayes' rule, common distributions.

2. [Linear Models](02_linear_models.md)
   Linear regression, logistic regression, softmax classification, regularisation; the maximum-likelihood view of MSE and cross-entropy.

3. [Introduction to Neural Networks](03_intro_to_neural_networks.md)
   Perceptron, MLPs, activations, the universal approximation theorem, why depth helps.

4. [Convolutional Neural Networks](04_convolutional_neural_networks.md)
   Convolutions, strides, padding, pooling, receptive fields, weight sharing — the inductive biases that make CNNs work for images.

5. [Deep Architecture Evolution](05_deep_architecture_evolution.md)
   LeNet → AlexNet → VGG → Inception/GoogLeNet → ResNet → DenseNet → MobileNet/EfficientNet. Why each step mattered.

6. [Backpropagation & Optimization](06_backpropagation_and_optimization.md)
   Computational graphs, backprop, vanishing/exploding gradients; SGD, momentum, AdaGrad, RMSProp, Adam; learning-rate schedules.

7. [Performance Estimation & Generalization](07_performance_estimation.md)
   Train/val/test, k-fold CV, bootstrapping, learning curves; external testing; classification metrics, ROC/AUC, calibration.

8. [Data Augmentation](08_data_augmentation.md)
   Geometric and photometric augmentations, Cutout, Mixup, CutMix, AutoAugment; label smoothing; what helps at training vs inference time.

9. [Recurrent Neural Networks](09_recurrent_neural_networks.md)
   RNNs, BPTT, LSTM/GRU gates, bidirectional & stacked RNNs, seq2seq.

10. [Vision Transformers](10_vision_transformers.md)
    Attention, self-attention, multi-head attention, the Transformer block; ViT and Swin Transformer; image captioning.

11. [Information Theory & DL Theory](11_information_theory.md)
    Entropy, cross-entropy, KL divergence, mutual information; the Information Bottleneck view of deep nets.

12. [Object Detection](12_object_detection.md)
    Single-instance + localization, two-stage (R-CNN, Faster R-CNN) and one-stage (YOLO, SSD, RetinaNet) detectors, anchors, IoU, NMS, focal loss, FPN, DETR; precision/recall and mAP.

13. [Image Segmentation](13_image_segmentation.md)
    Semantic, instance, and panoptic segmentation; encoder-decoder architectures, transposed convolutions, U-Net, atrous convolutions and DeepLabv3+, Mask R-CNN, SAM.

14. [Distribution Shifts](14_distribution_shifts.md)
    Covariate, label, and concept shifts; importance-weighted ERM, density ratio estimation, DRO; comparing distributions with IPMs (Wasserstein) and φ-divergences (KL).

## Reference

- **[Glossary](glossary.md)** — alphabetic reference of terms across all lectures.

## How to Use These Notes

- **First pass**: read every lecture summary in order. Time-box yourself.
- **Second pass**: focus on lectures you scored low on; cross-reference the glossary.
- **Final pass**: skim the "Key Takeaways" sections at the bottom of each file.

Each lecture file is self-contained — formulae, intuitions, and examples are inlined so you don't need the slides next to you.

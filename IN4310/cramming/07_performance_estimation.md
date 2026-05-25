# Lecture 7 — Performance Estimation & Generalization Beyond the Development Dataset

## Internal Testing (Recap)

### Train / Validation / Test split
- **Development dataset** is randomly partitioned:
  - **Train**: fit model parameters.
  - **Validation** (a.k.a. "tune"): pick hyperparameters / model class / optimizer.
  - **Test**: held-out, used **once** at the end to estimate the generalization error of the final model.
- Typical ratio: 70 / 15 / 15.

### Unbiased estimation
- The test subset must be set aside **before** the experiment.
- Run **only one** analysis on it; ideally pre-specify the metric.
- "If you don't like the test results, you have to obtain a completely new test set." — repeated peeking biases the estimate.

### Resampling for validation
When data is limited, fixed splits waste data. Two alternatives:
- **k-fold cross-validation**: partition the non-test data into k folds; rotate which fold serves as validation; average performance.
  - Pros: less variance from a single split, gives k models for ensembling.
  - Cons: training cost × k. Common k = 5.
- **Bootstrapping**: sample with replacement to form a train subset of the same size; the ~36.8% not selected become the validation subset. Repeat many times.

> Multiple comparisons make the best-looking validation choice **overly optimistic** — repeated tuning on the same validation set leaks information.

### Subset sizes & learning curves
- Performance vs training-set size is roughly linear on a log-log scale (power-law region between small-data and irreducible-error regions).
- Big learning gains come early; later doublings give little.

## External Testing & Representativity
- A test subset drawn from the development dataset is unbiased **for the development distribution**, not necessarily for the **intended application**.
- Examples of mismatch:
  - Coin scratch detector trained on clean coins fails on real coins with scratches.
  - Skin-lesion classifier "learns" surgical markers as a melanoma cue.
  - Pneumonia model exploits hospital-specific token markers (which correlate with disease prevalence).
- **External datasets** (different geography, time, equipment, demographics) give more realistic estimates of generalization. Use them **only once** to evaluate the final model.
- Empirical concern: pre-specified analyses on external sets are rarely reported.

## Domain Shift
The training data and the deployment distribution can differ in many ways: lighting, demographics, equipment, time. This drives most real-world deployment failures.

## Facilitating Generalization
A grab-bag of techniques to make models generalize better:

### Control network capacity
- Reduce trainable parameters.
- **Dropout**.
- **Weight decay** (L2 regularization).

### Facilitate learning
- **Residual connections**, **batch normalization**, **transfer learning**, **learning-rate schedules**, choice of optimizer.

### Image-specific normalization
- Per-image standardization (subtract image mean, divide by image std) — reduces visual variability between images.
- Cropping / centering region of interest.

### Data augmentation
- Random distortions of brightness, color, contrast, rotation, sharpness, scale during training.
- Increases effective dataset size; complements natural variation. (Lecture 8 goes into detail.)

## Performance Metrics

### Confusion matrix
- For k classes: `n_{a,b}` = #samples actually a, predicted b. Diagonal = correct.
- Binary case: TP, FP, TN, FN. (FP = type I error, FN = type II error.)

### Threshold-dependent metrics (one operating point)
- **Accuracy** = (TP+TN)/(TP+TN+FP+FN). Misleading on imbalanced data, fine-grained classification, or when uncertainty matters.
- **Top-1 / Top-5 accuracy**: is the highest / is one of the top 5 predictions correct?
- **Precision** = TP/(TP+FP) — accuracy of positive predictions.
- **Recall = Sensitivity = TPR** = TP/(TP+FN) — proportion of actual positives caught.
- **Specificity = TNR** = TN/(TN+FP). FPR = 1 − Specificity.
- **F1** = 2·Precision·Recall/(Precision+Recall) — harmonic mean. `F_β` weights recall β times more than precision.
- **Balanced accuracy** = (TPR + TNR) / 2 (multi-class: average per-class recall). Useful for **imbalanced** data.

### Threshold-free metrics (rank-based, summarize over all thresholds)
- **Precision-Recall (PR) curve**: precision vs recall over thresholds.
- **Average Precision (AP)** = area under PR curve. Perfect = 1, random ≈ positive class rate.
- **mAP** (mean AP): average AP over classes — standard in object detection.
- **ROC curve**: sensitivity vs (1 − specificity).
- **AUROC** = area under ROC. Equals the probability that a positive sample's score > a negative sample's score.

### Limits of AP / AUROC
- They use only the **ranking** of scores → invariant to any strictly increasing transform of scores.
- Risk: a calibration shift (e.g., scores raised to a power) can leave AUROC = 1 while the threshold-based decision becomes nearly all-negative.
- Mitigation: report a **classification metric** (like balanced accuracy) alongside AUROC/AP — it can expose biases that ranking metrics hide.

## Estimator Uncertainty
- A performance estimate from a finite test set has noise. Always report uncertainty.
- **95% confidence interval** is the standard. Note: for a concrete CI, the truth either is in it or isn't; the 95% refers to the *procedure*.
- Specific intervals for some metrics (e.g., binomial proportion: Clopper-Pearson, normal approximation for sensitivities).
- **Bootstrap confidence intervals** are a generic option:
  - Resample the test set with replacement (e.g., 10 000 times).
  - Compute the metric each time → form a distribution → take the 2.5th and 97.5th percentiles.
  - Tends to be a bit narrow; can be unreliable on small datasets.
- An over-optimistic test estimate ⇒ over-optimistic CI. Don't use the same external data for both model selection and final evaluation.

## Key Takeaways
- Lock the test set away. Use validation/CV for model selection. Use external data for the most realistic estimates.
- Always pre-specify the metric and pick one that **reflects the intended use** of the model.
- Imbalanced data → use balanced accuracy / F1; threshold-free → AP / AUROC, but pair with a classification metric.
- Report performance as **value ± confidence interval**, computed via bootstrap or a parametric formula.

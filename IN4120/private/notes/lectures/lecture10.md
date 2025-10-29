# Text classification 1
- give the text som sort of key words to make the search manage the text more easier

### Standing queries
- path from IR to text classification
    - you have a query that needs to have its data updated periodically
    - update it every now and then

### Categorization/Classification
- make a classification function that can take a document and give a tag from a fixed set of classes C = {c_1, c_2,...c_j}

- supervised learning
    - a document d
    - fixed set of classes, C
    - a training set D of documents each with their labels in C
    - create a classifier, gamma

    - bag of words:
        - just take the words and put it in a table with the times they occur in the document
        - train the model on the table
        - have a form for feature selection to reduce the processing time and make it more effecient
            - removes noise, helps avoiding overfitting 
    
        -evaluation must be done on data that doesnt contain the test data, split it up maybe?

- knn - k-fold cross validation
    - divide it in n sized blocks
    - use m out of n blocks for training and n-m for evaluating
    - systematically try out every option

- binary classifier
    - sens(t), y-axis
    - 1-spec(t), x-axis
    - if you get a curve that is a straight line towards the top right then its more or less just guessing when trying to classify
    - it should be a line straight up the straight to the right, the bigger the values the better
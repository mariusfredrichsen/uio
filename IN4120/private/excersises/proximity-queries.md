Adapt the linear merge of postings to handle proximity queries. Can you make it work for any value of k?

Example:
LIMIT! /3 STATUE /3 FEDERAL /2 TORT
- Again, here, /k means "within k words of"
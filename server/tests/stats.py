import string

prefix = input("Put the file prefix: ")
source = open(prefix + "_vader2.txt", 'r')
all = 0
all_len = 0
false_pos = 0
false_pos_len = 0
false_neg = 0
false_neg_len = 0
neu = 0
neu_len = 0
line = source.readline()
min_pos = len(line)
max_pos = len(line)
min_neg = len(line)
max_neg = len(line)
min_neu = len(line)
max_neu = len(line)
while line != "":
    all = all + 1
    sentence = line
    length = len(sentence)
    line = source.readline()
    if all % 2000 == 0:
        print(all)
    file = line.split("\t")[0]
    vader = line.split("\t")[1]
    all_len = all_len + length
    if vader == "vader: POS" and file != "file: POS":
        false_pos_len = false_pos_len + length
        false_pos = false_pos + 1
        if length > max_pos:
            max_pos = length
        if length < min_pos:
            min_pos = length
    elif vader == "vader: NEG" and file != "file: NEG":
        false_neg_len = false_neg_len + length
        false_neg = false_neg + 1
        if length > max_neg:
            max_neg = length
        if length < min_neg:
            min_neg = length
    elif vader == "vader: NEU":
        neu = neu + 1
        neu_len = neu_len + length
        if length > max_neu:
            max_neu = length
        if length < min_neu:
            min_neu = length
    line = source.readline()
print("Average Length of all: " + str(all_len/all))
print("Average Length of neg: " + str(false_neg_len/false_neg))
print("Average Length of pos: " + str(false_pos_len/false_pos))
print("Average Length of neu: " + str(neu_len/neu))
print("neg minimun: " + str(min_neg) + "\tneg maximum: " + str(max_neg))
print("pos minimun: " + str(min_pos) + "\tpos maximum: " + str(max_pos))
print("neu minimun: " + str(min_neu) + "\tneu maximum: " + str(max_neu))

source.close()

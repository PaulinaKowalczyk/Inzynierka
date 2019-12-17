import string
import requests
import codecs
import json
from polish_vader_sentiment import SentimentIntensityAnalyzer


analyzer = SentimentIntensityAnalyzer()
prefix = input("Put the file prefix: ")
source = open(prefix + "_opinions", 'r')
output = open(prefix + "_vader2.txt", 'a')
output2 = open(prefix + "_wrongs2.txt", 'a')
all = 0
wrong = 0
sum = 0
for line in source.readlines()[all+1:]:
    if all % 1000 == 0:
        print(all)
    all = all +1
    splitted = line.split('","')
    vs = analyzer.polarity_scores(splitted[1])
    file = splitted[2].replace("\"", "").strip()
    if vs["compound"] > 0.05:
        vader = "POS"
    elif vs["compound"] < -0.05:
        vader = "NEG"
    else:
        vader = "NEU"
    output_line = splitted[1].strip() + "\nfile: " + file + "\tvader: " + vader + "\t" + str(vs) + "\n"
    output.write(output_line)

    if vader != file:
        wrong = wrong + 1
        #print(output_line + "wrongs: " + str(wrong) + "/" + str(all))
        output2.write(splitted[1].strip() + "\t" + file + "\t vader: " + vader + "\t" + str(vs)+ "\n")

source.close()
output.close()

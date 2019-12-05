import string
import requests
import codecs
import json
from polish_vader_sentiment import SentimentIntensityAnalyzer

analyzer = SentimentIntensityAnalyzer()
prefix = input("Put the file prefix: ")
source = open(prefix + "_opinions", 'r')
output = open(prefix + "_vader.txt", 'a')
output2 = open(prefix + "_wrongs.txt", 'a')
all = (14310 + 19704)//2
wrong = 1989 + 2624
for line in source.readlines()[all+1:]:
    all = all +1
    #print(line)
    splitted = line.split('","')
    # for part in splitted:
    #     print(part)
    vs = analyzer.polarity_scores(splitted[1])
    file = splitted[2].replace("\"", "").strip()
    if vs["compound"] > 0.1:
        vader = "POS"
    elif vs["compound"] < -0.1:
        vader = "NEG"
    else:
        vader = "NEU"
    output_line = splitted[1].strip() + "\nfile: " + file + "\tvader: " + vader + "\n"
    output.write(output_line)

    if vader != file:
        wrong = wrong + 1
        print(output_line + "wrongs: " + str(wrong) + "/" + str(all))
        output2.write(splitted[1].strip() + "\t" + file + "\t vader: " + vader + "\n")

source.close()
output.close()

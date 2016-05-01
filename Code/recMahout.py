# This program gives the list of Rated books and Recommended books for a particular userid

import sys
 
if len(sys.argv) != 5:
        print ("Arguments: userId userDataFilename(mahoutInput.txt) booksFilename(books.txt) recommendationFilename(mahoutOutput.txt)")
        sys.exit(1)
 
userId, userDataFilename, booksFilename, recommendationFilename = sys.argv[1:]
 
print ("Reading Books Descriptions........")
bookFile = open(booksFilename)
bookById = {}
for line in bookFile:
        tokens = line.split("|")
        bookById[tokens[0]] = tokens[1:]
bookFile.close()
 
print ("Reading Rated Books........")
userDataFile = open(userDataFilename)
ratedBookIds = []
for line in userDataFile:
        tokens = line.split(",")
        if tokens[0] == userId:
                ratedBookIds.append((tokens[1],tokens[2]))
userDataFile.close()
 
print ("Reading Recommendations........")
recommendationFile = open(recommendationFilename)
recommendations = []
for line in recommendationFile:
        tokens = line.split("\t")
        if tokens[0] == userId:
                bookIdAndScores = tokens[1].strip("[]\n").split(",")
                recommendations = [ bookIdAndScore.split(":") for bookIdAndScore in bookIdAndScores ]
                break
recommendationFile.close()

#print("\n")
print ("------------------------")
print ("There are {} Rated Books by user with id {}".format(len(ratedBookIds), userId))
print ("------------------------")
for bookId, rating in ratedBookIds:
        #print ("%s, rating=%s" % (bookById[bookId][0], rating))
        print ("{}, rating={}".format(bookById[bookId][0], rating))
print ("------------------------")

print ("10 Recommended Books for user with id {}".format(userId))
print ("------------------------")
for bookId, score in recommendations:
        #print ("%s, score=%s" % (bookById[bookId][0], score))
        print ("{}, score={}".format(bookById[bookId][0], score))
print ("------------------------")
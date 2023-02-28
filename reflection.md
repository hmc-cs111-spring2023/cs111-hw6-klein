# Reflection on implementing regular expressions of a DSL

## Which operators were easiest to implement and why?

I found the union, concatenation, star, and repetitions operators easier to implement. Once I was able to figure out the format of the extension, it was very easy to write all four operators. Between the hints, information from the previous assignment and my prior knowledge, I knew what each operator was supposed to represent which I then translated into being the operator definition.

## Which operators were most difficult to implement and why?

I found the apply operator difficult to implement for two reasons. First, I was struggling to understand the best way to write my code in a scala-y way. I had originally written a for loop, but was worried that it was not scala-y (functional). However, after asking on discord and going to office hours, I was able to understand that a for loop is actually very similar to map in scala, so it continues to be functional. In the end I implemented apply using recursion, but I now have a better understanding of the similarities between a scala for loop, map and recurison.

The other issue that I faced was understanding my base case for the recursion. I first thought that when there are no repetions (input to apply is 0), that the output should be Empty, an empty language. However, it turns out that it should actually be epsilon, which represents an empty word. Using epsilon makes much more sense, because we are able to add onto this empty word, even though we can't add on to the empty language.

## Comment on the design of this internal DSL

Write a few brief paragraphs that discuss:

- What works about this design? (For example, what things seem easy and
  natural to say, using the DSL?)
- What doesn't work about this design? (For example, what things seem
  cumbersome to say?)
- Think of a syntactic change that might make the language better. How would
  you implement it _or_ what features of Scala would prevent you from
  implementing it? (You don't have to write code for this part. You could say
  "I would use extension to..." or "Scala's rules for valid
  identifiers prevent...")

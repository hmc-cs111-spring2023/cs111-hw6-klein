package machines

import regex._
import dfa._

// Char -> RegularLanguage
given Conversion[Char, RegularLanguage] = Character(_) 

// String -> RegularLanguage
given Conversion[String, RegularLanguage] = _.map(Character(_)).reduce(Concat(_, _))

extension (exp1: RegularLanguage)
    // Adding operators to RegularLanguage
    def || (exp2: RegularLanguage) = Union(exp1, exp2)

    def ~ (exp2: RegularLanguage) = Concat(exp1, exp2)

    def <*> = Star(exp1)

    def <+> = Concat(exp1, Star(exp1))

    def apply(n: Int) : RegularLanguage =
        if (n == 1) then exp1 // Ask about this? I can't get it to work for n == 0, what is the base case?
        else exp1 ~ (apply(n - 1))

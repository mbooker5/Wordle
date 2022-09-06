package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // List of most common 4 letter words from: https://7esl.com/4-letter-words/
        val fourLetterWords =
            "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

        // Returns a list of four letter words as a list
        fun getAllFourLetterWords(): List<String> {
            return fourLetterWords.split(",")
        }

        // Returns a random four letter word from the list in all caps
        fun getRandomFourLetterWord(): String {
            val allWords = getAllFourLetterWords()
            val randomNumber = (0..allWords.size).shuffled().last()
            return allWords[randomNumber].uppercase()
        }

        var wordToGuess = getRandomFourLetterWord()

        fun checkGuess(guess: Editable) : String {
            var result = ""


            for (i in 0..3) {
                if (guess[i].uppercase() == wordToGuess[i].uppercase()) {
                    result += "O"
                }
                else if (guess[i].uppercase() in wordToGuess.uppercase()) {
                    result += "+"
                }
                else {
                    result += "X"
                }
            }
            return result
        }
        val button = findViewById<android.widget.Button>(R.id.guessWordButton)
        val inputText = findViewById<android.widget.EditText>(R.id.guessWordEditText)

        var guessNumber = 1
        val g1Text = findViewById<android.widget.TextView>(R.id.g1)
        val g1CheckText = findViewById<android.widget.TextView>(R.id.g1check)
        val g2Text = findViewById<android.widget.TextView>(R.id.g2)
        val g2CheckText = findViewById<android.widget.TextView>(R.id.g2check)
        val g3Text = findViewById<android.widget.TextView>(R.id.g3)
        val g3CheckText = findViewById<android.widget.TextView>(R.id.g3check)
        val word = findViewById<android.widget.TextView>(R.id.wordReveal)


        fun resetGame() : Unit {

            g1Text.text = ""
            g1CheckText.text = ""
            g2Text.text = ""
            g2CheckText.text = ""
            g3Text.text = ""
            g3CheckText.text = ""
            guessNumber = 1
            wordToGuess = getRandomFourLetterWord()
            word.text = ""
        }

        button.setOnClickListener{
            if (guessNumber == 1){
                g1Text.text = inputText.text
                g1CheckText.text = checkGuess(inputText.text)
                guessNumber++
            }
            else if (guessNumber == 2){
                g2Text.text = inputText.text
                g2CheckText.text = checkGuess(inputText.text)
                guessNumber++
            }
            else if (guessNumber == 3){
                g3Text.text = inputText.text
                g3CheckText.text = checkGuess(inputText.text)
                guessNumber++
                word.text = wordToGuess
                button.text = "RESET"
            }
            else if (guessNumber == 4){
                resetGame()
            }
        }
    }




}


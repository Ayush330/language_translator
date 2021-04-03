package com.example.language_translator

 class targetLanguage
 {

     //20 languages.

 companion object
 {
     fun check(a:String):String
     {

         if(a=="bengali") return "bn"

         else if(a=="catalan") return "ca"

         else if(a=="chinese") return "zn-CN"

         else if(a=="french") return "fr"

         else if(a=="german") return "de"

         else if (a=="greek") return "el"

         else if (a=="gujarati") return "gu"

         else if (a=="hindi") return "hi"

         else if (a=="irish") return "ga"

         else if (a=="italian") return "it"

         else if (a=="japenese") return "ja"

         else if (a=="kannada") return "kn"

         else if (a=="korean") return "ko"

         else if (a=="latin") return "la"

         else if (a=="spanish") return "es"

         else if (a=="tamil") return "ta"

         else if (a=="portugese") return "pt"

         else if (a=="russian") return "ru"

         else if (a=="polish")  return "pl"

         else if (a=="dutch") return "nl"

         else
             return "not found"
     }

 }
}
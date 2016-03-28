package com.example;

public class JavaJokes {


    public String getJoke(String source){

        switch (source) {

            case "java":
                return "Java's innate.  Seven isn't inner heaven.";
            case "android":
                return "Did Lore have nightmares about big Data? ";

            default:
                return "You're not funny.";

        }

    }

}

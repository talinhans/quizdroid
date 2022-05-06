package edu.uw.ischool.talin16.quizdroid

import edu.uw.ischool.talin16.quizdroid.models.Quiz
import edu.uw.ischool.talin16.quizdroid.models.Topic

class DummyData {
    companion object {
        val topic1 = Topic(
            "Math",
            "This is Maths short description",
            "This is Maths long description",
            listOf(
                Quiz("2+2", "4", "5", "6", "7", 1),
                Quiz("3+2", "4", "5", "6", "7", 2),
                Quiz("4+2", "4", "5", "6", "7", 3)
            )
        )
        val topic2 = Topic(
            "Physic",
            "This is Physics short description",
            "This is Physics long description",
            listOf(
                Quiz("what is formula of power", "VI", "R", "MG", "W", 1),
                Quiz("what is formula of kinetic energy", "1/2mv^2", "MG", "VI", "MGH", 1)
            )
        )
        val topic3 = Topic(
            "Marvel Super Heroes",
            "This is Marvel Super Heroes short description",
            "This is Marvel Super Heroes long description",
            listOf(
                Quiz("Who is iron man", "Tony Stark", "Bruce Banner", "Steve Rogers", "Thor", 1),
                Quiz("Who is hulk", "Tony Stark", "Bruce Banner", "Steve Rogers", "Thor", 2),
                Quiz(
                    "Who is captain america",
                    "Tony Stark",
                    "Bruce Banner",
                    "Steve Rogers",
                    "Thor",
                    3
                )
            )
        )
        val topic4 = Topic(
            "Music",
            "This is Music short description",
            "This is Music long description",
            listOf(
                Quiz("How many notes in an octave", "8", "3", "6", "7", 1),
                Quiz("How many sharp does B note has", "0", "1", "2", "3", 1)
            )
        )
    }
}
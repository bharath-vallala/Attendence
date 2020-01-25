package com.example.attenence.models;

import java.util.ArrayList;
import java.util.List;

public class subjects {
    String[] sem1={"Advanced Java Programming","Operating Systems","Software Engineering","Discrete Mathematics"};
    String[] sem2={"Programming in Python","Computer Networks","Design and Analysis of Algorithms","Automata Theory"};

    String[] sem3={"Programming in C#","Compiler Design","Big Data Analytics","Data Mining"};

    String[] sem4={"Computer Organization","Cloud Computing","Mobile Computing","Artificial Intelligence"};
    String[] sems={"Sem 1","Sem 2","Sem 3","Sem 4"};
ArrayList<String> Array=new ArrayList(){{
    add("MBA");
    add("BSC");
    add("B.com");
}};

    public ArrayList<String> getArray() {

        return Array;
    }

    ;
    public String[] getSems() {
        return sems;
    }

    public String[] getSem1() {
        return sem1;
    }

    public String[] getSem2() {
        return sem2;
    }

    public String[] getSem3() {
        return sem3;
    }

    public String[] getSem4() {
        return sem4;
    }
}

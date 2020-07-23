package com.abhijit.allinone;

import java.util.ArrayList;
import java.util.List;

public class AppGlobalSetting {

    static List Main_List = new ArrayList();
    static String occopassion="";

    // Functional and Occopassion code
    /*MAIN MENU*/
    final static String MAIN1="House Hold Work";
    final static String MAIN2="Education";
    final static String MAIN3="Medical Support";
    final static String MAIN4="Germent Shop";
    final static String MAIN5="Grocery Shop";
    final static String MAIN6="Vegitable Shop";
    final static String MAIN7="Meet&Fish Shop";
    final static String MAIN8="Building Construction";
    final static String MAIN9="Entertainment";
    /* ENTERTAINMENT*/
    final static String ENT1="Cinema/Events Support";
    final static String ENT2="Light Food Service";
    final static String ENT3="Bill Submission";
    final static String ENT4="Gym";
    final static String ENT5="Car Rent";
    final static String ENT6="Pension Service";
    /* HOUSE HOLD*/
    final static String HUS1="Painter";
    final static String HUS2="Plumbing";
    final static String HUS3="Electrician";
    final static String HUS4="Water Supply";
    final static String HUS5="Car Rent";
    final static String HUS6="Kitchen Work";
    final static String HUS7="Gas-refil";
    final static String HUS8="Currier Support";
    final static String HUS9="Pension Service";
    final static String HUS10="House Cleaning";
    final static String HUS11="Iron dress";
    /*CONSTRUNCTION*/
    final static String CON1="Contructor";
    final static String CON2="Sand Shop";
    final static String CON3="Painting shop";
    final static String CON4="Cement Shop";
    final static String CON5="Briks Shop";
    final static String CON6="Wood Shop";
    final static String CON7="Glass Shop";
    final static String CON8="Iron Shop";
    /* MEDICAL*/
    final static String MED1="Test Centre";
    final static String MED2="Home Medical Support";
    final static String MED3="Doctor";
    final static String MED4="Nurse";
    final static String MED5="Hospital";
    final static String MED6="Ambulance Booking";
    final static String MED7="Medicine Shop";
    /*EDUCATION*/
    final static String EUD1="Tutions";
    final static String EUD2="Book Stoll";
    final static String EUD3="Education inst";

  public void iniit_resource(){
      Main_List.add(MAIN1);
      Main_List.add(MAIN2);
      Main_List.add(MAIN3);
      Main_List.add(MAIN4);
      Main_List.add(MAIN5);
      Main_List.add(MAIN6);
      Main_List.add(MAIN7);
      Main_List.add(MAIN8);
      Main_List.add(MAIN9);


      Main_List.add(HUS1);
      Main_List.add(HUS2);
      Main_List.add(HUS3);
      Main_List.add(HUS4);
      Main_List.add(HUS5);
      Main_List.add(HUS6);
      Main_List.add(HUS7);
      Main_List.add(HUS8);
      Main_List.add(HUS9);
      Main_List.add(HUS10);
      Main_List.add(HUS11);

      Main_List.add(MED1);
      Main_List.add(MED2);
      Main_List.add(MED3);
      Main_List.add(MED4);
      Main_List.add(MED5);
      Main_List.add(MED6);
      Main_List.add(MED7);

      Main_List.add(CON1);
      Main_List.add(CON2);
      Main_List.add(CON3);
      Main_List.add(CON4);
      Main_List.add(CON5);
      Main_List.add(CON6);
      Main_List.add(CON7);
      Main_List.add(CON8);

      Main_List.add(EUD1);
      Main_List.add(EUD2);
      Main_List.add(EUD3);

      Main_List.add(ENT1);
      Main_List.add(ENT2);
      Main_List.add(ENT3);
      Main_List.add(ENT3);
      Main_List.add(ENT4);
      Main_List.add(ENT5);


  }

    public static String[] MainResource={
            MAIN1,
            MAIN2,
            MAIN3,
            MAIN3,
            MAIN4,
            MAIN5,
            MAIN6,
            MAIN7,
            MAIN8,
            MAIN9,
         /*Main complete*/
            HUS1,
            HUS2,
            HUS3,
            HUS4,
            HUS5,
            HUS6,
            HUS7,
            HUS8,
            HUS9,
            HUS10,
            HUS11,
        /*House end */
            EUD1,
            EUD2,
            EUD3,
        /* education end*/
            MED1,
            MED2,
            MED3,
            MED4,
            MED5,
            MED6,
            MED7,
       /*medical over*/
            CON1,
            CON2,
            CON3,
            CON4,
            CON5,
            CON6,
            CON7,
            CON8,
        /*construction over*/
            ENT1,
            ENT2,
            ENT3,
            ENT4,
            ENT5,
            ENT6
        /*entertain ment over*/

    };


    public static Integer[] thumbImages_main = {
            R.drawable.realestate, R.drawable.graduation,
            R.drawable.consult, R.drawable.dress_shop,
            R.drawable.grocery, R.drawable.veg_shop,
            R.drawable.meat_fish, R.drawable.construction,
            R.drawable.ent_icon /*, R.drawable.lovely_time,
            R.drawable.medical, R.drawable.me_time,
            R.drawable.paintbrush, R.drawable.playstore,
            R.drawable.settings, R.drawable.star,
            R.drawable.suggestion, R.drawable.team_time,
            R.drawable.person1, R.drawable.person2,
            R.drawable.person3, R.drawable.trash,
            R.drawable.ent2_icon, R.drawable.ent3_icon,
            R.drawable.calendar, R.drawable.food,
            R.drawable.tickcircle, R.drawable.tickinsidecircle,
            R.drawable.food, R.drawable.career,
            R.drawable.gym,R.drawable.ent_icon*/
    };

    public static String[] imagefooter_main = {
            MAIN1,MAIN2,
            MAIN3,MAIN4,
            MAIN5,MAIN6,
            MAIN7,MAIN8,
            MAIN9 /*, "Dummy1",
            "Dummy2", "Dummy3",
            "Dummy4", "Dummy5",
            "Dummy6", "Dummy7",
            "Dummy8", "Dummy9",
            "Dummy10", "Dummy11",
            "Dummy12", "Dummy13",
            "Dummy14", "Dummy15",
            "Dummy16", "Dummy17",
            "Dummy18", "Dummy19",
            "Dummy20", "Dummy21",
            "Dummy22","Dummy23"*/
    };




    public static Integer[] thumbImages_house = {
            R.drawable.realestate, R.drawable.graduation,
            R.drawable.consult, R.drawable.dress_shop,
            R.drawable.grocery, R.drawable.veg_shop,
            R.drawable.meat_fish, R.drawable.construction,
            R.drawable.ent_icon, R.drawable.lovely_time,
            R.drawable.medical/*, R.drawable.me_time,
            R.drawable.paintbrush, R.drawable.playstore,
            R.drawable.settings, R.drawable.star,
            R.drawable.suggestion, R.drawable.team_time,
            R.drawable.person1, R.drawable.person2,
            R.drawable.person3, R.drawable.trash,
            R.drawable.ent2_icon, R.drawable.ent3_icon,
            R.drawable.calendar, R.drawable.food,
            R.drawable.tickcircle, R.drawable.tickinsidecircle,
            R.drawable.food, R.drawable.career,
            R.drawable.gym,R.drawable.ent_icon*/
    };

    public static String[] imagefooter_house = {
            HUS1,HUS2,
            HUS3,HUS4,
            HUS5,HUS6,
            HUS7,HUS8,
            HUS9,HUS10,
            HUS11
           /* "Dummy2", "Dummy3",
            "Dummy4", "Dummy5",
            "Dummy6", "Dummy7",
            "Dummy8", "Dummy9",
            "Dummy10", "Dummy11",
            "Dummy12", "Dummy13",
            "Dummy14", "Dummy15",
            "Dummy16", "Dummy17",
            "Dummy18", "Dummy19",
            "Dummy20", "Dummy21",
            "Dummy22","Dummy23"*/
    };




    public static Integer[] thumbImages_education = {
            R.drawable.realestate, R.drawable.graduation,
            R.drawable.consult/*, R.drawable.dress_shop,
            R.drawable.grocery, R.drawable.veg_shop,
            R.drawable.meat_fish, R.drawable.construction,
            R.drawable.ent_icon, R.drawable.lovely_time,
            R.drawable.medical, R.drawable.me_time,
            R.drawable.paintbrush, R.drawable.playstore,
            R.drawable.settings, R.drawable.star,
            R.drawable.suggestion, R.drawable.team_time,
            R.drawable.person1, R.drawable.person2,
            R.drawable.person3, R.drawable.trash,
            R.drawable.ent2_icon, R.drawable.ent3_icon,
            R.drawable.calendar, R.drawable.food,
            R.drawable.tickcircle, R.drawable.tickinsidecircle,
            R.drawable.food, R.drawable.career,
            R.drawable.gym,R.drawable.ent_icon*/
    };

    public static String[] imagefooter_education = {
            EUD1,EUD2,
            EUD3/*, "Dummy1-1",
            "Dummy1-2","Dummy1-3",
            "Dummy1-4", "Dummy 1-5",
            "Dummy1-6", "Dummy1",
            "Dummy2", "Dummy3",
            "Dummy4", "Dummy5",
            "Dummy6", "Dummy7",
            "Dummy8", "Dummy9",
            "Dummy10", "Dummy11",
            "Dummy12", "Dummy13",
            "Dummy14", "Dummy15",
            "Dummy16", "Dummy17",
            "Dummy18", "Dummy19",
            "Dummy20", "Dummy21",
            "Dummy22","Dummy23"*/
    };





    public static Integer[] thumbImages_medical = {
            R.drawable.realestate, R.drawable.consult,
            R.drawable.grocery, R.drawable.veg_shop,
            R.drawable.meat_fish, R.drawable.construction,
            R.drawable.ent_icon/*, R.drawable.lovely_time,
            R.drawable.medical, R.drawable.me_time,
            R.drawable.paintbrush, R.drawable.playstore,
            R.drawable.settings, R.drawable.star,
            R.drawable.suggestion, R.drawable.team_time,
            R.drawable.person1, R.drawable.person2,
            R.drawable.person3, R.drawable.trash,
            R.drawable.ent2_icon, R.drawable.ent3_icon,
            R.drawable.calendar, R.drawable.food,
            R.drawable.tickcircle, R.drawable.tickinsidecircle,
            R.drawable.food, R.drawable.career,
            R.drawable.gym,R.drawable.ent_icon*/
    };

    public static String[] imagefooter_medical = {
            MED1,MED2,
            MED3,MED4,
            MED5,MED6,
            MED7/*, "Dummy1",
            "Dummy2", "Dummy3",
            "Dummy4", "Dummy5",
            "Dummy6", "Dummy7",
            "Dummy8", "Dummy9",
            "Dummy10", "Dummy11",
            "Dummy12", "Dummy13",
            "Dummy14", "Dummy15",
            "Dummy16", "Dummy17",
            "Dummy18", "Dummy19",
            "Dummy20", "Dummy21",
            "Dummy22","Dummy23"*/
    };




    public static Integer[] thumbImages_construction = {
            R.drawable.realestate, R.drawable.graduation,
            R.drawable.consult, R.drawable.dress_shop,
            R.drawable.grocery, R.drawable.veg_shop,
            R.drawable.meat_fish, R.drawable.construction
            /*R.drawable.ent_icon, R.drawable.lovely_time,
            R.drawable.medical, R.drawable.me_time,
            R.drawable.paintbrush, R.drawable.playstore,
            R.drawable.settings, R.drawable.star,
            R.drawable.suggestion, R.drawable.team_time,
            R.drawable.person1, R.drawable.person2,
            R.drawable.person3, R.drawable.trash,
            R.drawable.ent2_icon, R.drawable.ent3_icon,
            R.drawable.calendar, R.drawable.food,
            R.drawable.tickcircle, R.drawable.tickinsidecircle,
            R.drawable.food, R.drawable.career,
            R.drawable.gym,R.drawable.ent_icon*/
    };

    public static String[] imagefooter_construction = {
            CON1,CON2,
            CON3,CON4,
            CON5,CON6,
            CON7,CON8,
           /* "Dummy1-1", "Dummy1",
            "Dummy2", "Dummy3",
            "Dummy4", "Dummy5",
            "Dummy6", "Dummy7",
            "Dummy8", "Dummy9",
            "Dummy10", "Dummy11",
            "Dummy12", "Dummy13",
            "Dummy14", "Dummy15",
            "Dummy16", "Dummy17",
            "Dummy18", "Dummy19",
            "Dummy20", "Dummy21",
            "Dummy22","Dummy23"*/
    };






    public static Integer[] thumbImages_entertainment = {
            R.drawable.realestate, R.drawable.graduation,
            R.drawable.consult, R.drawable.dress_shop,
            R.drawable.grocery, R.drawable.veg_shop/*,
            R.drawable.meat_fish, R.drawable.construction,
            R.drawable.ent_icon, R.drawable.lovely_time,
            R.drawable.medical, R.drawable.me_time,
            R.drawable.paintbrush, R.drawable.playstore,
            R.drawable.settings, R.drawable.star,
            R.drawable.suggestion, R.drawable.team_time,
            R.drawable.person1, R.drawable.person2,
            R.drawable.person3, R.drawable.trash,
            R.drawable.ent2_icon, R.drawable.ent3_icon,
            R.drawable.calendar, R.drawable.food,
            R.drawable.tickcircle, R.drawable.tickinsidecircle,
            R.drawable.food, R.drawable.career,
            R.drawable.gym,R.drawable.ent_icon*/
    };

    public static String[] imagefooter_entertainment = {
            ENT1,ENT2,
            ENT3,ENT4,
            ENT5,ENT6/*,"Dummy1-1",
            "Dummy1-2", "Dummy1-3",
            "Dummy1-4", "Dummy1",
            "Dummy2", "Dummy3",
            "Dummy4", "Dummy5",
            "Dummy6", "Dummy7",
            "Dummy8", "Dummy9",
            "Dummy10", "Dummy11",
            "Dummy12", "Dummy13",
            "Dummy14", "Dummy15",
            "Dummy16", "Dummy17",
            "Dummy18", "Dummy19",
            "Dummy20", "Dummy21",
            "Dummy22","Dummy23"*/
    };


    public static int Getid(String row)
    {
        return Main_List.indexOf(row);
    }
    public static String  Getid(int id)
    {
        return (String)Main_List.get(id);
    }


}

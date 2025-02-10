public class StudentData{
    // NAME:SHAEEM ROCKCLIFFE
        //JDF is the exact day of the year that it is and the last two digits of the year  January 01 1998 in JDF is 00198
     
        public enum Months{
     
            JANUARY(0),
            FEBUARY(31),  // 28 or 29 depending on the leap year make sure to take account foor this at the end
            MARCH(59),
            APRIL(90),
            MAY(120),
            JUNE(151),
            JULY(181),
            AUGUST(212),
            SEPTEMBER(243),
            OCTOBER(273),
            NOVEMBER(304),
            DECEMBER(334);
     
            private final int value;
     
            Months(int value){
                this.value = value;
            }
     
            public int getValue(){
                return value;
            }
           
        }
     
     
        public static void main(String[] args){
            int[] date = {1,0,2,5,2,0,0,6};
            int monthnum1 = date[0] * 10;
            int monthnum = monthnum1 + date[1];  // month number
            int days1 = date[2] * 10;
            int days = days1 + date[3];  // days number
            String year1 = Integer.toString(date[6]);
            String year2 = Integer.toString(date[7]);
            switch(monthnum){
    
    
                case 1:
                Months monthsdays1 = Months.JANUARY;
                int monthdays101 = monthsdays1.getValue();
                System.out.println((monthdays101 + days) + year1 + year2);
                break;
                case 2:
                Months monthsdays2 = Months.FEBUARY;
                int monthdays102 = monthsdays2.getValue();
                System.out.println((monthdays102 + days) + year1 + year2);
                break;
                case 3:
                Months monthsdays3 = Months.MARCH;
                int monthdays103 = monthsdays3.getValue();
                System.out.println((monthdays103 + days) + year1 + year2);
                break;
                case 4:
                Months monthsdays4 = Months.APRIL;
                int monthdays104 = monthsdays4.getValue();
                System.out.println((monthdays104 + days) + year1 + year2);
                break;
                case 5:
                Months monthsdays5 = Months.MAY;
                int monthdays105 = monthsdays5.getValue();
                System.out.println((monthdays105 + days) + year1 + year2);
                break;
                case 6:
                Months monthsdays6 = Months.JUNE;
                int monthdays106 = monthsdays6.getValue();
                System.out.println((monthdays106 + days) + year1 + year2);
                break;
                case 7:
                Months monthsdays7 = Months.JULY;
                int monthdays107 = monthsdays7.getValue();
                System.out.println((monthdays107 + days) + year1 + year2);
                break;
                case 8:
                Months monthsdays8 = Months.AUGUST;
                int monthdays108 = monthsdays8.getValue();
                System.out.println((monthdays108 + days) + year1 + year2);
                break;
                case 9:
                Months monthsdays9 = Months.SEPTEMBER;
                int monthdays109 = monthsdays9.getValue();
                System.out.println((monthdays109 + days) + year1 + year2);
                break;
                case 10:
                Months monthsdays10 = Months.OCTOBER;
                int monthdays1010 = monthsdays10.getValue();
                System.out.println((monthdays1010 + days) + year1 + year2);
                break;
                case 11:
                Months monthsdays11 = Months.NOVEMBER;
                int monthdays1011 = monthsdays11.getValue();
                System.out.println((monthdays1011 + days) + year1 + year2);
                break;
                case 12:
                Months monthsdays12 = Months.DECEMBER;
                int monthdays1012 = monthsdays12.getValue();
                System.out.println((monthdays1012 + days) + year1 + year2);
                break;
               
     
               
     
            }
           
     
     
            }
     
       
       
     }
     
    
    
    
    
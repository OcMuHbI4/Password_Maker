import java.util.ArrayList;

public class PasswordGeneration {

    static boolean moreThanSixtyFour, without_razlichiya_check, without_razlichiya = true;
    static int passwordLength, generation,
            forDeletingRazlichiya, forDeletingGeneratedPasswords, kolvo_razlichiy = 0;
    static String generatedPassword = "";
    static boolean specSymbols, bigLetters, letters, numbers;
    static ArrayList<Integer> downGranici = new ArrayList<>();
    static ArrayList<Integer> plusForUpGranica = new ArrayList<>();
    static ArrayList<String> generatedPasswords = new ArrayList<>();
    static int generationNumbbb;
   static void GeneratePasswords() {

        String buffer = MainWindowClass.quantityOfSymbolsTextField.getText();

        try{
        passwordLength = Integer.parseInt(buffer);}
        catch (final NumberFormatException e){
            passwordLength = 0;
            MainWindowClass.errorLabel.setText("You didn't enter the quantity!");
        }

       if (passwordLength < 0){
           passwordLength *= -1;
       }

       if (passwordLength > 64){

           moreThanSixtyFour = true;
           passwordLength = 64;

       }

       if (!(passwordLength == 0)) {

           MainWindowClass.errorLabel.setText("");

           do {
               if (numbers) {
                   downGranici.add(48);
                   plusForUpGranica.add(10);

                   kolvo_razlichiy++;

                   without_razlichiya = false;
               }

               if (letters) {
                   downGranici.add(97);
                   plusForUpGranica.add(26);
                   kolvo_razlichiy++;

                   without_razlichiya = false;
               }

               if (bigLetters) {
                   downGranici.add(65);
                   plusForUpGranica.add(26);
                   kolvo_razlichiy++;

                   without_razlichiya = false;
               }

               if (specSymbols) {
                   downGranici.add(58);
                   plusForUpGranica.add(7);

                   downGranici.add(91);
                   plusForUpGranica.add(6);

                   downGranici.add(123);
                   plusForUpGranica.add(4);

                   kolvo_razlichiy = kolvo_razlichiy + 3;

                   without_razlichiya = false;

               }

               if (without_razlichiya) {
                   without_razlichiya_check = true;
                   numbers = true;
                   bigLetters = true;
                   letters = true;
               }

           } while (without_razlichiya);

           for (int j = 0; j < 5; j++) {

               generatedPassword = "";


               for (int i = 0; i < passwordLength; i++) {


                   generationNumbbb = ((int) (Math.random() * kolvo_razlichiy));

                   generation = (int) ((Math.random()
                           * plusForUpGranica.get(generationNumbbb)) + downGranici.get(generationNumbbb));

                   char c = (char) generation;

                   generatedPassword = generatedPassword + c;


               }

               generatedPasswords.add(generatedPassword);

           }

           for (int i = 0; i < generatedPasswords.size(); i++) {
               MainWindowClass.passwordsTextArea.setValueAt(generatedPasswords.get(i), i, 0);
           }

           kolvo_razlichiy = 0;

           forDeletingRazlichiya = downGranici.size();
           forDeletingGeneratedPasswords = generatedPasswords.size();

           for (int i = 0; i < forDeletingRazlichiya; i++) {
               downGranici.remove(0);
               plusForUpGranica.remove(0);
           }

           if (forDeletingGeneratedPasswords > 0) {
               generatedPasswords.subList(0, forDeletingGeneratedPasswords).clear();
           }

           if (without_razlichiya_check) {
               without_razlichiya_check = false;
               numbers = false;
               bigLetters = false;
               letters = false;

           }
           without_razlichiya = true;

           if(moreThanSixtyFour){
           MainWindowClass.errorLabel.setText("You entered more than 64!");
           moreThanSixtyFour = false;
           }
       }
    }
}
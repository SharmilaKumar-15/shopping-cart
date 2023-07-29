import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sh1 {
    public static void main(String[] args) throws IOException {
        String[] command = {"cmd"," /c ","powershell", "C:\\pass.ps1","Administrator" , "sharmi"};
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process=builder.start();
        BufferedReader outputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));  //outputstream

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        String s = null;
        String output = "";
        while ((s = stdInput.readLine()) != null) {
            output=output+s;
        }
        outputStream.close();
        System.out.println(output);
        System.out.println("output is ::" + output +"::" );


        /*ProcessBuilder builder = new ProcessBuilder(command).inheritIO();
        Process process=builder.start();

        BufferedReader outputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));  //outputstream

        String str ="";
        String output ="";

        if(outputStream.ready()) {
            while((str = outputStream.readLine()) != null)
            {
                output += str +"\n";
            }
        }
        outputStream.close();
        System.out.println(output);*/

    }
}
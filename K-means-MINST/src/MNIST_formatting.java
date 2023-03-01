import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class MNIST_formatting
{
    FileInputStream  training_set;


    // this reads 100 training examples to test the kmeans
    public static  double[][] read_file()
    {
        try {
            FileInputStream training_set = new FileInputStream("mnist_train.csv");
            Scanner reader = new Scanner(training_set);
            double[][] dataset = new double[1000][];
            reader.useDelimiter(",");
            for (int j = 0; j < 1000; j++)
            {
                String data = reader.nextLine();
                String[] arr = Arrays.copyOfRange(data.split(","),1,785);
                double[] iarr = new double[784];
                for (int i =0 ; i < arr.length; i++)// loads without label
                {
                    iarr[i] = Double.parseDouble(arr[i]);//+1 cause of the label
                }
                dataset[j]= iarr;
            }
            System.out.println(dataset[0].length);
            return dataset;





        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        return  null;
    }
}

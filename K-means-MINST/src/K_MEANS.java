import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileNotFoundException;

public class K_MEANS
{
    double[][] centroid;//number of centroids stay constant

    double[][] datapoints;

    LinkedList<Integer>[] clusters;

    int num_clusters;

    public K_MEANS(double[][] dataset,int ncluster)
    {
        datapoints=dataset;
        num_clusters=ncluster;
        generate_centroids();
        clusters = new LinkedList[num_clusters];
    }
    public void assign_to_clusters()
    {
        for(int i=0;i<datapoints.length;i++)// for each of the datapoints
        {
            int index=-1;
            double min_distance = 1e9;
            for(int j=0;j<centroid.length;j++)// for each of the centroids
            {
                double distance =e_distance(datapoints[i],centroid[j]);
                if(distance<min_distance)
                {
                    index = j;
                    min_distance=distance;
                }
            }
            if(index==-1)
            {
                System.out.println("Massive Error: The datapoint as been asigned to nothing");
                return;
            }
            if(clusters[index]==null)
            {
                clusters[index] = new LinkedList<Integer>();
                clusters[index].add(i);//index is the location of the centroid and cluster
                // i is the index of the data point
            }
            else
            {
                clusters[index].add(i);// just add the index to the cluster
            }

        }
    }


    public void update_clusters()
    {
        for(int i=0;i<centroid.length;i++)
        {
            LinkedList<Integer> cluster_of_centroid = clusters[i];//loads all the indexes into a variable

            if(cluster_of_centroid == null)
            {
                continue;// if no datapoint is part of this cluster then it is never assigned to and therefore
                // this variable will be null
                // in this case we don't want to change the the location of the centroid and therefore we continue
            }

            centroid[i]=average_cluster(cluster_of_centroid);// takes the average position of each element in the cluster
            // and then assigns the average as the new position of the cluster


        }


    }

    public static double e_distance(double[] a, double[] b)
    {
        double sum_square = 0;
        for(int i=0;i<784;i++)
        {
            sum_square+=Math.pow(a[i]-b[i],2);
        }
        //System.out.println(Math.sqrt(sum_square));
        return Math.sqrt(sum_square);
    }

    public void generate_centroids()
    {
        Random rand = new Random();
        centroid = new double[num_clusters][];
        for(int i=0;i<num_clusters;i++)
        {
            double[] cent = new double[784];
            for(int j=0;j<784;j++)
            {
                //cent[j]=rand.nextInt(0,256);
                cent[j]= ThreadLocalRandom.current().nextInt(0, 256);
            }
            centroid[i]=cent;
        }
    }

    public double[] sum(double[] a,double[] b)
    {
        double[] ans=new double[784];
        if(!(a.length==784 && b.length==784))
        {
            System.out.println("EEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRRRRRROOOOOOOOOOOOOOOOOOOOOOOOOORRRRRRRRRRRRR");
            return null;
        }
        for(int i=0;i<a.length;i++)
        {
            ans[i]=a[i]+b[i];
        }
        return ans;
    }
    public double[] average_cluster(LinkedList<Integer> group)
    {
        //group contains the indexes of the datapoints which are part of the group
        double[] average= new double[784];
        int size = group.size();
        for(int i=0;i<size;i++)
        {
            average = sum(average,datapoints[group.get(i)]);
        }

        for(int i=0;i<average.length;i++)
        {
            average[i]=average[i]/ size;
        }
        return average;

    }

    public void train()
    {
        //one training iteration
        assign_to_clusters();
        update_clusters();
    }


    public void save_clusters()
    {
        try
        {
            File store = new File("clusters.txt");
            if(store.createNewFile())
            {
                System.out.println("File Created");
            }
        }
        catch(IOException e)
        {
            System.out.println("File bad");
        }
        try
        {
            FileWriter writer = new FileWriter("clusters.txt");
            writer.write(Arrays.deepToString(centroid));
        }
        catch (IOException e)
        {
            System.out.println("error");
        }
    }


}


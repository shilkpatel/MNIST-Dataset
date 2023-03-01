import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class kMeans
{
    double[][] centroid;// has the value of the clusters

    double[][] datapoints; // has all the datapoints
    //seems to be null somewhat

    LinkedList<Integer>[] clusters; //  has the cluster information which is the index of the data point
    // this can be done as the data points stay constant and is only read from

    int num_clusters;

    public kMeans(double[][] data,int cluster)
    {
        datapoints=data;
        num_clusters=cluster;
        generate_centroids();
    }

    public double[] asign_cluster(double[] data,int datai)
    {
        /// finds the cluster which
        double[] min = new double[784];// some datapoints are not being assigned to a cluster
        double min_distance =1000000000.0;
        int index=0;
        for(int i =0;i<num_clusters;i++)// for each of the clusters it sees which has the lowest distance
        {

            double dist=e_distance(data,centroid[i]);
            //System.out.println(dist);
            //System.out.println(dist<min_distance);
            if(dist<min_distance)
            {

                min=centroid[i];//value of best node
                min_distance=dist;// euclidean distance from node
                index=i;//index of best node
            }
        }
        if(clusters[index]==null)
        {
            clusters[index] = new LinkedList<Integer>();
            clusters[index].add(datai);//needs to be index of data point but is index of cluster
        }
        else
        {
            clusters[index].add(datai);
        }
        return min;
    }

    public void update_cluster()
    {
        for(int i=0;i< centroid.length;i++)
        {
            LinkedList<Integer> cluster= clusters[i];// some clusters get nothing assigned to them
            if(!(cluster==null)){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                centroid[i]=average_cluster(cluster);// this function is wiping the centroid

            }
            else
            {
                //System.out.println(Arrays.toString(centroid[i]));
            }
            //System.out.println(cluster.toString());

        }
    }

    public double[] average_cluster(LinkedList<Integer> group)
    {
        double[] sum = new double[784];
        double num_elements=group.size();
        if(num_elements==0){
            System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        }
        for(int j =0;j<num_elements;j++)
        {
            //System.out.println(Arrays.toString(datapoints[cluster.get(j)]));
            sum= sum_data(datapoints[group.get(j)],sum);
            System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
            System.out.println(Main.sum_values(datapoints[group.get(j)]));
            //System.out.println(Arrays.toString(sum));//group.get(j) gets the index of the element in the cluster
            // then sums it to the total sum
        }
        double[] cent = new double[784];
        for(int j=0;j<num_elements;j++)
        {
            cent[j]=sum[j]/num_elements;
        }
        return cent;
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
                cent[j]=ThreadLocalRandom.current().nextInt(0, 256);
            }
            centroid[i]=cent;
        }
    }

    public static double[] sum_data(double[] a,double[] b)
    {
        //System.out.println(a.length);
        double[] c = new double[a.length];
        for(int i=0;i<a.length;i++)
        {
            c[i] =a[i]+b[i];
        }
        return c;
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

    public void train()
    {
        clusters = new LinkedList[num_clusters];// this88u
        for(int i=0;i< datapoints.length;i++)
        {
            System.out.println(i);
            asign_cluster(datapoints[i],i);
        }
        update_cluster();
    }

    public void total_data_points()
    {
        int total =0;
        for (LinkedList<Integer> i: clusters)
        {
            try {
                total += i.size();
            }
            catch (NullPointerException e )
            {
                total+=0;
            }
        }
        System.out.println(total);
    }
}

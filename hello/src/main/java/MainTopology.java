import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

public class MainTopology {
    public static void main(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("IntegerSpout", new IntegerSpout());
        builder.setBolt("MultiplierBolt", new MultiplierBolt()).shuffleGrouping("IntegerSpout");

        Config conf = new Config();
        conf.setDebug(true);


        LocalCluster cluster = new LocalCluster();
        try {
            cluster.submitTopology("HelloTopology", conf, builder.createTopology());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cluster.shutdown();
        }
    }
}

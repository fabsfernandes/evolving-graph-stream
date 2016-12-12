
package br.ufu.lsi.gephi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.MultiNode;
import org.graphstream.ui.layout.springbox.implementations.LinLog;

import br.ufu.lsi.gephi.model.Call;
import br.ufu.lsi.gephi.model.CallType;
import br.ufu.lsi.gephi.model.Direction;
import br.ufu.lsi.gephi.utils.FileUtil;


/**
 * Toy stream visualizer for call graph dataset
 * 
 * read all files named CALL_DETAILS_201212<sequence_number>.txt
 * 
 * Good reference: http://graphstream-project.org/doc/Tutorials/Graph-Visualisation/1.0/
 *  
 */
public class CallGraphStreamVisualizer {
    
    private static final String DATASET_FILE = "/Users/fabiola/Desktop/Datasets/CallGraphs/201212-1/CALL_DETAILS_201212";
    
    private static final Integer NUMBER_OF_FILES = 22;
    
    public static void main( String... args ) throws Exception {

        Graph graph = new MultiGraph( "CallGraph" );

        //String style = "node{fill-mode:plain;fill-color:#567;size:6px;text-mode:normal;}"
        //        + "edge{text-mode:hidden;}";
        
        //String style = "node{fill-mode:plain;fill-color:#567;size:6px;text-mode:hidden;}node.important {fill-color: red;size: 35px;}"
        //                + "edge{text-mode:hidden;}";
        
        // dynamic size
        String style = "node{fill-mode:plain;fill-color:#567;size-mode:dyn-size;size:10px;text-mode:hidden;}node.important {fill-color: red;size: 35px;}"
                + "edge{text-mode:hidden;}";
        
        graph.addAttribute( "stylesheet", style );
        graph.setAutoCreate( true );
        graph.setStrict( false );

        LinLog layout = new LinLog( false );
        graph.addSink( layout );
        graph.display();
        
        for ( int i = 1; i < NUMBER_OF_FILES; i++ ) {

            try {
                String fileNumber;
                if ( i < 10 )
                    fileNumber = "0" + i;
                else
                    fileNumber = "" + i;
                
                BufferedReader br = FileUtil.openInputFile( DATASET_FILE + fileNumber + ".txt" );
                System.out.println( "Day = " + i );
                String line;
                int count = 0;
                while ( ( line = br.readLine() ) != null ) {

                    Call call = generateCall( line );

                    if ( call != null ) {
                        
                            MultiNode numberA = graph.addNode( call.getNumberA() );
                            numberA.setAttribute( "label", call.getNumberA() );

                            MultiNode numberB = graph.addNode( call.getNumberB() );
                            numberB.setAttribute( "label", call.getNumberB() );

                            String label = numberA.getId() + "-" + numberB.getId() + ",";
                            label += String.valueOf( call.getDuration() ) + "sec";
                            Calendar cal = Calendar.getInstance();
                            cal.setTime( call.getDay() );
                            label += "," + cal.get( Calendar.DAY_OF_MONTH ) + "/"
                                    + cal.get( Calendar.MONTH );

                            Edge edge = graph.addEdge( label, numberA, numberB, true );
                            edge.addAttribute( "duration", call.getDuration() );
                            edge.addAttribute( "date", call.getDay() );
                            edge.addAttribute( "time", call.getTime() );
                            edge.addAttribute( "label", label );
                            
                            Iterator<Node> iterator = graph.getNodeIterator();
                            while( iterator.hasNext() ) {
                                Node node = iterator.next();
                                System.out.println( node.getLabel( "label" ));
                                
                                    node.addAttribute( "ui.size", (node.getOutDegree()+1)*1);
                           
                            }
                            

                            System.out.println( edge.getLabel( "label" ) );

                           //}
                        //if ( count % 100 == 0 ) {
                        //    System.out.println( count );
                            Thread.sleep( 100 );
                        //}


                        count++ ;
                    }

                }
            } catch ( FileNotFoundException e ) {

            }
        }

    }

    public static Call generateCall( String line ) throws Exception {
        String[] tokens = line.split( "\\|" );

        if ( ! tokens[ 0 ].equals( "DATE" ) ) {

            Call call = new Call();

            DateFormat format = new SimpleDateFormat( "yyyyMMdd", Locale.ENGLISH );
            Date date = format.parse( tokens[ 0 ] );
            call.setDay( date );
            call.setTime( Long.parseLong( tokens[ 1 ] ) );
            call.setDuration( Long.parseLong( tokens[ 2 ] ) );
            call.setNumberA( tokens[ 3 ] );
            call.setNumberB( tokens[ 4 ] );
            call.setContractA( Long.parseLong( tokens[ 5 ] ) );
            if ( ! tokens[ 6 ].isEmpty() )
                call.setContractB( Long.parseLong( tokens[ 6 ] ) );
            call.setStartCellId( Long.parseLong( tokens[ 7 ] ) );
            call.setEndCellId( Long.parseLong( tokens[ 8 ] ) );
            call.setDirection( Direction.valueOf( tokens[ 9 ] ) );
            call.setCallType( CallType.valueOf( tokens[ 10 ] ) );
            call.setDestinationType( CallType.valueOf( tokens[ 11 ] ) );

            if ( tokens[ 12 ].equals( "Y" ) )
                call.setDroppedCall( true );
            else
                call.setDroppedCall( false );

            if ( tokens[ 13 ].equals( "Y" ) )
                call.setVoiceMail( true );
            else
                call.setVoiceMail( false );

            return call;
        }
        return null;
    }

}

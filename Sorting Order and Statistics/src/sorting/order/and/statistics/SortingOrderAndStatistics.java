/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sorting.order.and.statistics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.ChartUtilities; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.axis.NumberAxis; 
import org.jfree.chart.axis.NumberTickUnit; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.renderer.xy.XYItemRenderer; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

/**
 *
 * @author Heidi Chiu
 */
public class SortingOrderAndStatistics {

    public static class Stat {
        int comparisons;
        int size;

        Stat() {
            this.comparisons = 0;
            this.size = 0;
        }
        
        Stat(int size) {
            this.comparisons = 0;
            this.size = size;
        }
    }

    public static class SortingAlgorithms {

        public static Stat selectionSort(ArrayList<Integer> numbers) {
            Stat stats = new Stat(numbers.size());
            for (int i = 0; i < numbers.size(); i++)  {  
                int index = i;  
                stats.comparisons++;
                for (int j = i + 1; j < numbers.size(); j++){  
                    stats.comparisons++;
                    stats.comparisons++;
                    if (numbers.get(j) < numbers.get(index)){  
                        index = j;
                    }  
                }  
                int smallerNumber = numbers.get(index);   
                numbers.set(index, numbers.get(i));
                numbers.set(i, smallerNumber);
            }  
            return stats;
        }

        public static Stat bubbleSort(ArrayList<Integer> numbers) {
            Stat stats = new Stat(numbers.size());
            int n = numbers.size();  
            int temp = 0;  
            for(int i=0; i < n; i++){  
                stats.comparisons++;
                for(int j=1; j < (n-i); j++){  
                    if(numbers.get(j-1) > numbers.get(j)){ 
                        stats.comparisons++;
                        stats.comparisons++;
                        temp = numbers.get(j-1);  
                        numbers.set(j-1, numbers.get(j));
                        numbers.set(j, temp);
                   }  
                }  
             }  
            return stats;
        }

        public static Stat insertionSort(ArrayList<Integer> numbers) {
            Stat stats = new Stat(numbers.size());
            int n = numbers.size();  
            for (int j = 0; j < n; j++) {  
                int key = numbers.get(j);  
                int i = j-1;  
                stats.comparisons++;
                while ( (i > -1) && ( numbers.get(i) > key ) ) { 
                    stats.comparisons++;
                    stats.comparisons++;
                    numbers.set(i+1, numbers.get(i));
                    i--;  
                    
                }  
                numbers.set(i+1, key);
            }  
            return stats;
        }
        
        public static ArrayList<Integer> insertionSort2(ArrayList<Integer> numbers, Stat stats) {
            int n = numbers.size();  
            for (int j = 0; j < n; j++) {  
                int key = numbers.get(j);  
                int i = j-1;  
                stats.comparisons++;
                while ( (i > -1) && ( numbers.get(i) > key ) ) { 
                    stats.comparisons++;
                    stats.comparisons++;
                    numbers.set(i+1, numbers.get(i));
                    i--;  
                    
                }  
                numbers.set(i+1, key);
            }  
            return numbers;
        }

        public static Stat insertionSort3(ArrayList<Integer> numbers) {
            Stat stats = new Stat(numbers.size());
            int n = numbers.size();  
            for (int j = 0; j < n; j++) {  
                int key = numbers.get(j);  
                int i = j-1;  
                stats.comparisons++;
                while ( (i > -1) && ( numbers.get(i) > key ) ) { 
                    stats.comparisons++;
                    numbers.set(i+1, numbers.get(i));
                    i--;  
                    
                }  
                numbers.set(i+1, key);
            }  
            return stats;
        }
        
        public static ArrayList<Integer> insertionSort4(ArrayList<Integer> numbers, Stat stats) {
            int n = numbers.size();  
            for (int j = 0; j < n; j++) {  
                int key = numbers.get(j);  
                int i = j-1;  
                stats.comparisons++;
                while ( (i > -1) && ( numbers.get(i) > key ) ) { 
                    stats.comparisons++;
                    numbers.set(i+1, numbers.get(i));
                    i--;  
                    
                }  
                numbers.set(i+1, key);
            }  
            return numbers;
        }
        
        public static Stat shellSort(ArrayList<Integer> numbers, ArrayList<Integer> gaps) {
            Stat stats = new Stat(numbers.size());
            int gap;
            for (int k = 0; k < gaps.size(); k++){
                gap = gaps.get(k);
                for (int i = gap; i < numbers.size(); i ++){
                    stats.comparisons++;
                    int temp = numbers.get(i);
                    int j;            
                    for (j = i; j >= gap && numbers.get(j - gap) > temp; j -= gap) {
                        stats.comparisons++;
                        numbers.set(j, numbers.get(j - gap));
                    }
                    numbers.set(j, temp);
                }
            }
            return stats;
        }

        public static Stat bucketSort(ArrayList<Integer> numbers, int bucketSize) {
            Stat stats = new Stat(numbers.size());
            
            int min = Collections.min(numbers);
            int max = Collections.max(numbers);
            int range = max - min;
            int distance;
            
            
            if(bucketSize < 1) {
                distance = range + 1;
                bucketSize = numbers.size();
            } else {
                if(bucketSize > numbers.size()) {
                    distance = range + 1;
                } else {
                    distance = range/(numbers.size()/bucketSize) + 1;
                }
            }
            ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>(numbers.size()/bucketSize);
            ArrayList<Integer> subbucket = new ArrayList<Integer>();
            
            for(int i = 0; i < numbers.size()/bucketSize; i++) {
                buckets.add(subbucket);
            }
                   
            for(int i = 0; i < numbers.size(); i++) {
                for(int j = 0; j < numbers.size()/bucketSize; j++) {
                    subbucket = new ArrayList<Integer>();
                    stats.comparisons++;
                    if(numbers.get(i) < (min + (distance*(j+1)))) {
                        for(int k = 0; k < buckets.get(j).size(); k++) {
                            subbucket.add(buckets.get(j).get(k));
                        }
                        subbucket.add(numbers.get(i));
                        subbucket = insertionSort2(subbucket, stats);
                        buckets.set(j, subbucket);
                        break;
                    } 
                }
            }
            
            return stats;
        }

        public static ArrayList<Integer> bucketSort2(ArrayList<Integer> numbers, int bucketSize) {
            Stat stats = new Stat(numbers.size());
            
            int min = Collections.min(numbers);
            int max = Collections.max(numbers);
            int range = max - min;
            int distance = range/(numbers.size()/bucketSize) + 1;
            ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>(numbers.size()/bucketSize);
            ArrayList<Integer> subbucket = new ArrayList<Integer>();
            
            for(int i = 0; i < numbers.size()/bucketSize; i++) {
                buckets.add(subbucket);
            }
                   
            for(int i = 0; i < numbers.size(); i++) {
                for(int j = 0; j < numbers.size()/bucketSize; j++) {
                    subbucket = new ArrayList<Integer>();
                    stats.comparisons++;
                    if(numbers.get(i) < (min + (distance*(j+1)))) {
                        for(int k = 0; k < buckets.get(j).size(); k++) {
                            subbucket.add(buckets.get(j).get(k));
                        }
                        subbucket.add(numbers.get(i));
                        subbucket = insertionSort2(subbucket, stats);
                        buckets.set(j, subbucket);
                        break;
                    } 
                }
            }
            
            ArrayList<Integer> finalarr = new ArrayList<Integer>();
            for(int i = 0; i < buckets.size(); i++) {
                for(int j = 0; j < buckets.get(i).size(); j++) {
                    finalarr.add(buckets.get(i).get(j));
                }
            }
//            System.out.println(buckets);
//            System.out.println(finalarr);
            return finalarr;
        }
        
        public static Stat bucketSort3(ArrayList<Integer> numbers, int bucketSize) {
            Stat stats = new Stat(numbers.size());
            
            int min = Collections.min(numbers);
            int max = Collections.max(numbers);
            int range = max - min;
            int distance;
            
            
            if(bucketSize < 1) {
                distance = range + 1;
                bucketSize = numbers.size();
            } else {
                if(bucketSize > numbers.size()) {
                    distance = range + 1;
                } else {
                    distance = range/(numbers.size()/bucketSize) + 1;
                }
            }
            ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>(numbers.size()/bucketSize);
            ArrayList<Integer> subbucket = new ArrayList<Integer>();
            
            for(int i = 0; i < numbers.size()/bucketSize; i++) {
                buckets.add(subbucket);
            }
                   
            for(int i = 0; i < numbers.size(); i++) {
                for(int j = 0; j < numbers.size()/bucketSize; j++) {
                    subbucket = new ArrayList<Integer>();
                    stats.comparisons++;
                    if(numbers.get(i) < (min + (distance*(j+1)))) {
                        for(int k = 0; k < buckets.get(j).size(); k++) {
                            stats.comparisons++;
                            subbucket.add(buckets.get(j).get(k));
                        }
                        subbucket.add(numbers.get(i));
                        subbucket = insertionSort4(subbucket, stats);
                        buckets.set(j, subbucket);
                        break;
                    } 
                }
            }
            
            return stats;
        }
        
        public static Stat radixSort(ArrayList<Integer> numbers) {
            Stat stats = new Stat(numbers.size());
            
            int m = Collections.max(numbers);
 
            for (int exp = 1; m/exp > 0; exp *= 10) {
                stats = bucketSort3(numbers, 1);
            }
            
            return stats;
        }
        
        public static Stat radixSort2(ArrayList<Integer> numbers) {
            Stat stats = new Stat(numbers.size());
            
            int m = Collections.max(numbers);
 
            for (int exp = 1; m/exp > 0; exp *= 10) {
                stats = insertionSort3(numbers);
            }
            
            return stats;
        }

        public static Stat mergeSort(ArrayList<Integer> numbers, int low, int high) {
            Stat stats = new Stat(numbers.size());
            int N = high - low;         
            if (N <= 1) {
                stats.comparisons++;
                return stats;
            }
            int mid = low + N/2; 
            mergeSort(numbers, low, mid); 
            mergeSort(numbers, mid, high); 
            int[] temp = new int[N];
            int i = low, j = mid;
            for (int k = 0; k < N; k++) {
                stats.comparisons++;
                if (i == mid)  {
                    temp[k] = numbers.get(j);
                    j++;
                }
                else if (j == high) {
                    temp[k] = numbers.get(i);
                    i++;
                }
                else if (numbers.get(j) < numbers.get(i))  {
                    temp[k] = numbers.get(j);
                    j++;
                }
                else {
                    temp[k] = numbers.get(i);
                    i++;
                }
            }    
            for (int k = 0; k < N; k++) {
                stats.comparisons++;
                numbers.set(low + k, temp[k]);
            }
            return stats;
        }

        public static Stat quickSort(ArrayList<Integer> numbers, int low, int high) {
            Stat stats = new Stat(numbers.size());
            int pi;
            stats.comparisons++;
            if (low < high) {
                pi = numbers.get(high);
                int i = (low-1); 
                for (int j=low; j<high; j++) {
                    stats.comparisons++;
                    if (numbers.get(j) <= pi) {
                        i++;
                        int temp = numbers.get(i);
                        numbers.set(i, numbers.get(j));
                        numbers.set(j, temp);
                    }
                }
                int temp = numbers.get(i+1);
                numbers.set(i+1, numbers.get(high));
                numbers.set(high, temp);
                
                pi = i + 1;
                
                quickSort(numbers, low, pi - 1);
                quickSort(numbers, pi + 1, high);
            }
            return stats;
        }

    }
    
    public static final class QuadraticWorstGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();
        ArrayList<Stat> stat3 = new ArrayList<Stat>();

        public QuadraticWorstGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true, false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesPaint( 2 , new Color(0xACA287) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            renderer.setSeriesStroke( 2 , new BasicStroke( 8.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel );
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Quadratic_Worst.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            InputStream in = new FileInputStream(new File("descending.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.selectionSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat2.add(SortingAlgorithms.bubbleSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat3.add(SortingAlgorithms.insertionSort(numbers));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries selection = new XYSeries( "Selection" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                selection.add(x, y);
            }

            XYSeries bubble = new XYSeries( "Bubble" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                bubble.add(x, y);
            }

            XYSeries insertion = new XYSeries( "Insertion" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                insertion.add(x, y);
            }        
            
            dataset.addSeries( selection );          
            dataset.addSeries( bubble );          
            dataset.addSeries( insertion );
            return dataset;
        }
        
    }
    
    public static final class QuadraticRandomGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();
        ArrayList<Stat> stat3 = new ArrayList<Stat>();

        public QuadraticRandomGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" , createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesPaint( 2 , new Color(0xACA287) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            renderer.setSeriesStroke( 2 , new BasicStroke( 8.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Quadratic_Random.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            InputStream in = new FileInputStream(new File("random.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.selectionSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat2.add(SortingAlgorithms.bubbleSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat3.add(SortingAlgorithms.insertionSort(numbers));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries selection = new XYSeries( "Selection" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                selection.add(x, y);
            }

            XYSeries bubble = new XYSeries( "Bubble" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                bubble.add(x, y);
            }

            XYSeries insertion = new XYSeries( "Insertion" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                insertion.add(x, y);
            }        

            dataset.addSeries( selection );          
            dataset.addSeries( bubble );          
            dataset.addSeries( insertion );

            return dataset;
        }
        
    }
    
    public static final class QuadraticBestGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();
        ArrayList<Stat> stat3 = new ArrayList<Stat>();

        public QuadraticBestGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesPaint( 2 , new Color(0xACA287) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            renderer.setSeriesStroke( 2 , new BasicStroke( 8.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Quadratic_Best.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            InputStream in = new FileInputStream(new File("ascending.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.selectionSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat2.add(SortingAlgorithms.bubbleSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat3.add(SortingAlgorithms.insertionSort(numbers));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries selection = new XYSeries( "Selection" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                selection.add(x, y);
            }

            XYSeries bubble = new XYSeries( "Bubble" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                bubble.add(x, y);
            }

            XYSeries insertion = new XYSeries( "Insertion" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                insertion.add(x, y);
            }        
            
            dataset.addSeries( selection );          
            dataset.addSeries( bubble );          
            dataset.addSeries( insertion );

            return dataset;
        }
        
    }
    
    public static final class InsertionvShellWorstGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();

        public InsertionvShellWorstGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Insertion_vs_Shell_Worst.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            ArrayList gaps = new ArrayList<Integer>();
            InputStream in = new FileInputStream(new File("descending.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.insertionSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                for(int k = 0; k < numbers.size(); k++) {
                    for(int j = k; j/2 > 0; j = j/2) {
                        gaps.add(j);
                    }
                }
                this.stat2.add(SortingAlgorithms.shellSort(numbers, gaps));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries insertion = new XYSeries( "Insertion" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                insertion.add(x, y);
            }

            XYSeries shell = new XYSeries( "Shell" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                shell.add(x, y);
            }     
            
            dataset.addSeries( insertion );          
            dataset.addSeries( shell );        

            return dataset;
        }
    }
    
    public static final class InsertionvShellRandomGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();

        public InsertionvShellRandomGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Insertion_vs_Shell_Random.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            ArrayList gaps = new ArrayList<Integer>();
            InputStream in = new FileInputStream(new File("random.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.insertionSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                for(int k = 0; k < numbers.size(); k++) {
                    for(int j = k; j/2 > 0; j = j/2) {
                        gaps.add(j);
                    }
                }
                this.stat2.add(SortingAlgorithms.shellSort(numbers, gaps));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries insertion = new XYSeries( "Insertion" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                insertion.add(x, y);
            }

            XYSeries shell = new XYSeries( "Shell" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                shell.add(x, y);
            }     
            
            dataset.addSeries( insertion );          
            dataset.addSeries( shell );        

            return dataset;
        }
    }
    
    public static final class InsertionvShellBestGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();

        public InsertionvShellBestGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Insertion_vs_Shell_Best.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            ArrayList gaps = new ArrayList<Integer>();
            InputStream in = new FileInputStream(new File("ascending.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.insertionSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                for(int k = 0; k < numbers.size(); k++) {
                    for(int j = k; j/2 > 0; j = j/2) {
                        gaps.add(j);
                    }
                }
                this.stat2.add(SortingAlgorithms.shellSort(numbers, gaps));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries insertion = new XYSeries( "Insertion" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                insertion.add(x, y);
            }

            XYSeries shell = new XYSeries( "Shell" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                shell.add(x, y);
            }     
            
            dataset.addSeries( insertion );          
            dataset.addSeries( shell );        

            return dataset;
        }
    }

    public static final class ShellRandomGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();

        public ShellRandomGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Shell_Random.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            ArrayList gaps = new ArrayList<Integer>();
            InputStream in = new FileInputStream(new File("random.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                for(int k = 0; k < numbers.size(); k++) {
                    for(int j = k; j/2 > 0; j = j/2) {
                        gaps.add(j);
                    }
                }
                this.stat1.add(SortingAlgorithms.shellSort(numbers, gaps));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                gaps = new ArrayList<Integer>();
                gaps.add(1);
                gaps.add(4);
                gaps.add(10);
                gaps.add(23);
                gaps.add(57);
                this.stat2.add(SortingAlgorithms.shellSort(numbers, gaps));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries shell = new XYSeries( "Shell's" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                shell.add(x, y);
            }

            XYSeries ciura = new XYSeries( "Ciura's" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                ciura.add(x, y);
            }
            
            dataset.addSeries( shell );          
            dataset.addSeries( ciura );       

            return dataset;
        }
    }
    
    public static final class BucketWorstGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();
        ArrayList<Stat> stat3 = new ArrayList<Stat>();
        ArrayList<Stat> stat4 = new ArrayList<Stat>();
        ArrayList<Stat> stat5 = new ArrayList<Stat>();

        public BucketWorstGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true, false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesPaint( 2 , new Color(0xACA287) );
            renderer.setSeriesPaint( 3 , Color.BLUE );
            renderer.setSeriesPaint( 4 , Color.GREEN );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 2.5f ) );
            renderer.setSeriesStroke( 2 , new BasicStroke( 4.0f ) );
            renderer.setSeriesStroke( 3 , new BasicStroke( 5.5f ) );
            renderer.setSeriesStroke( 4 , new BasicStroke( 7.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel );
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Bucket_Worst.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            InputStream in = new FileInputStream(new File("descending.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.bucketSort(numbers, 1));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat2.add(SortingAlgorithms.bucketSort(numbers, numbers.size()/2));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat3.add(SortingAlgorithms.bucketSort(numbers, 5));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat4.add(SortingAlgorithms.bucketSort(numbers, numbers.size()));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat5.add(SortingAlgorithms.bucketSort(numbers, 10));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries b1 = new XYSeries( "1" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                b1.add(x, y);
            }

            XYSeries b2 = new XYSeries( "n/2" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                b2.add(x, y);
            }

            XYSeries b3 = new XYSeries( "5" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                b3.add(x, y);
            }       
            
            XYSeries b4 = new XYSeries( "n" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                b4.add(x, y);
            }

            XYSeries b5 = new XYSeries( "10" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                b5.add(x, y);
            }  
            
            dataset.addSeries( b1 );          
            dataset.addSeries( b2 );          
            dataset.addSeries( b3 );
            dataset.addSeries( b4 );          
            dataset.addSeries( b5 );
            return dataset;
        }
    }
    
    public static final class BucketRandomGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();
        ArrayList<Stat> stat3 = new ArrayList<Stat>();
        ArrayList<Stat> stat4 = new ArrayList<Stat>();
        ArrayList<Stat> stat5 = new ArrayList<Stat>();

        public BucketRandomGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true, false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesPaint( 2 , new Color(0xACA287) );
            renderer.setSeriesPaint( 3 , Color.BLUE );
            renderer.setSeriesPaint( 4 , Color.GREEN );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 2.5f ) );
            renderer.setSeriesStroke( 2 , new BasicStroke( 4.0f ) );
            renderer.setSeriesStroke( 3 , new BasicStroke( 5.5f ) );
            renderer.setSeriesStroke( 4 , new BasicStroke( 7.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel );
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Bucket_Random.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            InputStream in = new FileInputStream(new File("random.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.bucketSort(numbers, 1));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat2.add(SortingAlgorithms.bucketSort(numbers, numbers.size()/2));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat3.add(SortingAlgorithms.bucketSort(numbers, 5));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat4.add(SortingAlgorithms.bucketSort(numbers, numbers.size()));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat5.add(SortingAlgorithms.bucketSort(numbers, 10));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries b1 = new XYSeries( "1" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                b1.add(x, y);
            }

            XYSeries b2 = new XYSeries( "n/2" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                b2.add(x, y);
            }

            XYSeries b3 = new XYSeries( "5" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                b3.add(x, y);
            }       
            
            XYSeries b4 = new XYSeries( "n" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                b4.add(x, y);
            }

            XYSeries b5 = new XYSeries( "10" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                b5.add(x, y);
            }  
            
            dataset.addSeries( b1 );          
            dataset.addSeries( b2 );          
            dataset.addSeries( b3 );
            dataset.addSeries( b4 );          
            dataset.addSeries( b5 );
            return dataset;
        }
    }
    
    public static final class BucketBestGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();
        ArrayList<Stat> stat3 = new ArrayList<Stat>();
        ArrayList<Stat> stat4 = new ArrayList<Stat>();
        ArrayList<Stat> stat5 = new ArrayList<Stat>();

        public BucketBestGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true, false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesPaint( 2 , new Color(0xACA287) );
            renderer.setSeriesPaint( 3 , Color.BLUE );
            renderer.setSeriesPaint( 4 , Color.GREEN );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 2.5f ) );
            renderer.setSeriesStroke( 2 , new BasicStroke( 4.0f ) );
            renderer.setSeriesStroke( 3 , new BasicStroke( 5.5f ) );
            renderer.setSeriesStroke( 4 , new BasicStroke( 7.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel );
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Bucket_Best.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            InputStream in = new FileInputStream(new File("ascending.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.bucketSort(numbers, 1));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat2.add(SortingAlgorithms.bucketSort(numbers, numbers.size()/2));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat3.add(SortingAlgorithms.bucketSort(numbers, 5));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat4.add(SortingAlgorithms.bucketSort(numbers, numbers.size()));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                this.stat5.add(SortingAlgorithms.bucketSort(numbers, 10));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries b1 = new XYSeries( "1" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                b1.add(x, y);
            }

            XYSeries b2 = new XYSeries( "n/2" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                b2.add(x, y);
            }

            XYSeries b3 = new XYSeries( "5" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                b3.add(x, y);
            }       
            
            XYSeries b4 = new XYSeries( "n" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                b4.add(x, y);
            }

            XYSeries b5 = new XYSeries( "10" );          
            for(int i = 0; i<stat3.size(); i++) {
                x = (double)stat3.get(i).size;
                y = (double)stat3.get(i).comparisons;
                b5.add(x, y);
            }  
            
            dataset.addSeries( b1 );          
            dataset.addSeries( b2 );          
            dataset.addSeries( b3 );
            dataset.addSeries( b4 );          
            dataset.addSeries( b5 );
            return dataset;
        }
    }
    
    public static final class RadixWorstGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();

        public RadixWorstGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Element Access" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Radix_Worst.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            ArrayList gaps = new ArrayList<Integer>();
            InputStream in = new FileInputStream(new File("descending.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.radixSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                for(int k = 0; k < numbers.size(); k++) {
                    for(int j = k; j/2 > 0; j = j/2) {
                        gaps.add(j);
                    }
                }
                this.stat2.add(SortingAlgorithms.radixSort2(numbers));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries counting = new XYSeries( "Counting Sort subroutine" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                counting.add(x, y);
            }

            XYSeries insertion = new XYSeries( "Insertion Sort subroutine" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                insertion.add(x, y);
            }     
              
            dataset.addSeries( counting );        
            dataset.addSeries( insertion );  
            
            return dataset;
        }
    }
 
    public static final class RadixRandomGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();

        public RadixRandomGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Element Access" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Radix_Random.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            ArrayList gaps = new ArrayList<Integer>();
            InputStream in = new FileInputStream(new File("random.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.radixSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                for(int k = 0; k < numbers.size(); k++) {
                    for(int j = k; j/2 > 0; j = j/2) {
                        gaps.add(j);
                    }
                }
                this.stat2.add(SortingAlgorithms.radixSort2(numbers));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries counting = new XYSeries( "Counting Sort subroutine" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                counting.add(x, y);
            }

            XYSeries insertion = new XYSeries( "Insertion Sort subroutine" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                insertion.add(x, y);
            }     
              
            dataset.addSeries( counting );        
            dataset.addSeries( insertion );  
            
            return dataset;
        }
    }
    
    public static final class RadixBestGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();

        public RadixBestGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Element Access" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Radix_Best.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            ArrayList gaps = new ArrayList<Integer>();
            InputStream in = new FileInputStream(new File("ascending.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.radixSort(numbers));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    numbers.add(Integer.parseInt(st.nextToken()));
                }
                for(int k = 0; k < numbers.size(); k++) {
                    for(int j = k; j/2 > 0; j = j/2) {
                        gaps.add(j);
                    }
                }
                this.stat2.add(SortingAlgorithms.radixSort2(numbers));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries counting = new XYSeries( "Counting Sort subroutine" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                counting.add(x, y);
            }

            XYSeries insertion = new XYSeries( "Insertion Sort subroutine" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                insertion.add(x, y);
            }     
              
            dataset.addSeries( counting );        
            dataset.addSeries( insertion );  
            
            return dataset;
        }
    }
    
    public static final class MergevQuickRandomGraph extends ApplicationFrame {
        
        ArrayList<Stat> stat1 = new ArrayList<Stat>();
        ArrayList<Stat> stat2 = new ArrayList<Stat>();

        public MergevQuickRandomGraph( String applicationTitle, String chartTitle ) throws IOException {
           super(applicationTitle);
           JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle ,"Size of Array" ,"Comparisons" ,createDataset() ,PlotOrientation.VERTICAL ,true , true , false);

            ChartPanel chartPanel = new ChartPanel( xylineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 800 ) );
            XYPlot plot = xylineChart.getXYPlot( );
            
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setRange(0, 100);
            domain.setTickUnit(new NumberTickUnit(10));
            domain.setVerticalTickLabels(true);
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(0, 3000);
            range.setTickUnit(new NumberTickUnit(200));
            
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
            renderer.setSeriesPaint( 0 , new Color(0x3F2B2C) );
            renderer.setSeriesPaint( 1 , new Color(0xEC3047) );
            renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
            renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
            plot.setRenderer( renderer ); 
            setContentPane( chartPanel ); 
            
            int width = 1200;   /* Width of the image */
            int height = 800;  /* Height of the image */ 
            File XYChart = new File( "Graphs\\Merge_vs_Quick_Random.jpeg" ); 
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        }

        public XYDataset createDataset() throws IOException {
            ArrayList numbers = new ArrayList<Integer>();
            StringTokenizer st;
            ArrayList<String> texts = new ArrayList<String>();
            InputStream in = new FileInputStream(new File("random.txt"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            
            while((line = bufferedReader.readLine()) != null) {
                texts.add(line);
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat1.add(SortingAlgorithms.mergeSort(numbers, 0, numbers.size()));
            }
            
            for(int i = 0; i < 100; i++) {
                numbers = new ArrayList<Integer>();
                st = new StringTokenizer(texts.get(i));
                while(st.hasMoreTokens()) {
                    int input = Integer.parseInt(st.nextToken());
                    numbers.add(input);
                }
                this.stat2.add(SortingAlgorithms.quickSort(numbers, 0, numbers.size()-1));
            }
            
            XYSeriesCollection dataset = new XYSeriesCollection( );      
            double x;
            double y;
            
            XYSeries merge = new XYSeries( "Merge Sort" );  
            for(int i = 0; i<stat1.size(); i++) {
                x = (double)stat1.get(i).size;
                y = (double)stat1.get(i).comparisons;
                merge.add(x, y);
            }

            XYSeries quick = new XYSeries( "Quick Sort" );   
            for(int i = 0; i<stat2.size(); i++) {
                x = (double)stat2.get(i).size;
                y = (double)stat2.get(i).comparisons;
                quick.add(x, y);
            }
            
            dataset.addSeries( merge );          
            dataset.addSeries( quick );       

            return dataset;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList numbers = new ArrayList<Integer>();
        StringTokenizer st;
        String array = null;
        Stat stats = new Stat();
        String readthis = "descending.txt";
        ArrayList<String> texts = new ArrayList<String>();
        int testsize = 100;
               
        try {
            FileReader fileReader = new FileReader(readthis);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            new QuadraticWorstGraph("O(n^2) algorithms", "O(n^2) Worst Case");
            new QuadraticRandomGraph("O(n^2) algorithms", "O(n^2) Random Case");
            new QuadraticBestGraph("O(n^2) algorithms", "O(n^2) Best Case");
            new InsertionvShellWorstGraph("Insertion vs Shell", "Insertion vs Shell Worst Case");
            new InsertionvShellRandomGraph("Insertion vs Shell", "Insertion vs Shell Random Case");
            new InsertionvShellBestGraph("Insertion vs Shell", "Insertion vs Shell Best Case");
            new ShellRandomGraph("Shell's vs Ciura's", "Shell's vs Ciura's Random Case");
            new BucketWorstGraph("Bucket Sort", "Bucket Sort Worst Case");
            new BucketRandomGraph("Bucket Sort", "Bucket Sort Random Case");
            new BucketBestGraph("Bucket Sort", "Bucket Sort Best Case");
            new RadixWorstGraph("Radix Sort", "Radix Sort Worst Case");
            new RadixRandomGraph("Radix Sort", "Radix Sort Random Case");
            new RadixBestGraph("Radix Sort", "Radix Sort Best Case");
            new MergevQuickRandomGraph("Merge vs Quick", "Merge vs Quick Random Case");
            
            numbers = new ArrayList<Integer>();
            fileReader = new FileReader(readthis);
            bufferedReader = new BufferedReader(fileReader);
            for(int i = 0; i < testsize; i++) {
                array = bufferedReader.readLine();
            }
            st = new StringTokenizer(array);
            while(st.hasMoreTokens()) {
                numbers.add(Integer.parseInt(st.nextToken()));
            }
            stats = SortingAlgorithms.mergeSort(numbers, 0, numbers.size());
            System.out.println("MERGE: " + stats.comparisons);
            bufferedReader.close();
            
            
            numbers = new ArrayList<Integer>();
            fileReader = new FileReader(readthis);
            bufferedReader = new BufferedReader(fileReader);
            for(int i = 0; i < testsize; i++) {
                array = bufferedReader.readLine();
            }
            st = new StringTokenizer(array);
            while(st.hasMoreTokens()) {
                numbers.add(Integer.parseInt(st.nextToken()));
            }
            stats = SortingAlgorithms.quickSort(numbers, 0, numbers.size()-1);
            System.out.println("QUICK: " + stats.comparisons);
            bufferedReader.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file totallyRandom.txt");   
        } catch (IOException ex) {
            System.out.println("Error reading file totallyRandom.txt");
        }
    }
    
}

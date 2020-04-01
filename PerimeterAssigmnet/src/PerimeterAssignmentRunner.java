import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    /*
     * Complete writing the method getNumPoints that has one parameter s that is
     * of type Shape. This method returns an integer that is the number of points in Shape s.
     * Add code in the method testPerimeter to call getNumPoints and to print the result.
     */
    public int getNumPoints (Shape s) {
        // Put code here
        int count=0;

        for(Point p:s.getPoints())
            count++;
        return count;
    }

    /*
     *  Complete writing the method getAverageLength that has one parameter s that is of type Shape.
     *  This method returns a number of type double that is the calculated average of all the sides’
     *  lengths in the Shape S.Add code in the method testPerimeter to call the method
     *  getAverageLength and to print out the result.
     */
    public double getAverageLength(Shape s) {
        // Put code here
        double totalPerim=0.0;
        double count=0.0;

        Point prevPt = s.getLastPoint();

        for (Point currPt : s.getPoints())
        {
            double currDist = prevPt.distance(currPt);

            totalPerim = totalPerim + currDist;

            prevPt = currPt;

            count++;
        }
        double average=totalPerim/count;

        return average;
    }


    /*
     * Complete writing the method getLargestSide that has one parameter s that is of type Shape.
     * This method returns a number of type double that is the longest side in the Shape S.
     * Add code in the method testPerimeter to call the method getLargestSide and to print out the result.
     */
    public double getLargestSide(Shape s) {

        double max=0.0;

        Point prevPt = s.getLastPoint();

        for (Point currPt : s.getPoints())
        {
            double currDist = prevPt.distance(currPt);

            if(max<currDist)
                max=currDist;

            prevPt = currPt;
        }

        return max;
    }


    /*
     * Complete writing the method getLargestX that has one parameter s that is of type Shape.
     * This method returns a number of type double that is the largest x value over all the points in the Shape s.
     * Add code in the method testPerimeter to call the method getLargestX and to print out the result.
     */
    public double getLargestX(Shape s) {

        double largeX=0;

        for (Point currPt : s.getPoints()) {
            if(currPt.getX()>largeX)
                largeX=currPt.getX();
        }

        return largeX;

    }


    /*
     * Complete writing the method getLargestPerimeterMultipleFiles that has no parameters.
     * It needs to return the the largest perimeter over all the shapes in the files you have selected.
     */
    public double getLargestPerimeterMultipleFiles() {

        double max=0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s=new Shape(fr);
            double largestPerimeter=getPerimeter(s);
            if(max<largestPerimeter)
                max=largestPerimeter;

        }

        return max;
    }


    /*
     * Finish writing the method getFileWithLargestPerimeter that has no parameters.
     * This method should, like the getLargestPerimeterMultipleFiles method, create its own Directory Resource, except that this
     *  new method returns the File that has the largest such perimeter, so it has return type File.
     */
    public String getFileWithLargestPerimeter() {

        double max=0.0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s=new Shape(fr);
            double largestPerimeter=getPerimeter(s);
            if(max<largestPerimeter)
                max=largestPerimeter;
            temp=f;
        }
        return temp.getName();
    }


    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);

        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        int totalPoints=getNumPoints(s);
        System.out.println("totalPoints :"+totalPoints);

        double averageLength=getAverageLength(s);
        System.out.println("averageLength "+averageLength);

        double largestSide=getLargestSide(s);
        System.out.println("largest side "+largestSide);

        double largestX=getLargestX(s);

        System.out.println("largest ordinate "+largestX);
    }

    public void testPerimeterMultipleFiles() {

        double largestPerimetrinMulFiles=getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter in files "+largestPerimetrinMulFiles);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here

        String fileName=getFileWithLargestPerimeter();
        System.out.println("file name of largest perimeter is "+fileName);

    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}

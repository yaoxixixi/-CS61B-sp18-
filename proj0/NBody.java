

public class NBody {
    public static double readRadius(String path){
        In in= new In(path);
        int first =in.readInt();
        double second=in.readDouble();
        return second;
    }
    public static Planet[] readPlanets(String path){
        In in =new In(path);
        int num=in.readInt();
        Planet[] p=new Planet[num];
        double first=in.readDouble();
        for(int i=0;i<num;i++){
            double a1=in.readDouble();
            double a2=in.readDouble();
            double a3=in.readDouble();
            double a4=in.readDouble();
            double a5=in.readDouble();
            String a6=in.readString();
            p[i]=new Planet(a1,a2,a3,a4,a5,a6);
        }
        return p;
    }
    public static void main(String[] args){

        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double R=readRadius(filename);
        Planet[] p=readPlanets(filename);


        StdDraw.enableDoubleBuffering();
/*
        String img="images/starfield.jpg";
        StdDraw.setScale(-R,R);
        StdDraw.clear();
        StdDraw.picture(0,0,img);

        for(int i=0;i<p.length;i++){
            p[i].draw();
        }
        StdDraw.show();
*/
        for(int t=0;t<T;t+=dt){
            double xF[] =new double[5];
            double yF[] =new double[5];
            for(int i=0;i<p.length;i++){
                xF[i]=p[i].calcNetForceExertedByX(p);
                yF[i]=p[i].calcNetForceExertedByY(p);
            }
            for(int j=0;j<p.length;j++){
                p[j].update(dt,xF[j],yF[j]);
            }
            String img="images/starfield.jpg";
            StdDraw.setScale(-R,R);
            StdDraw.clear();
            StdDraw.picture(0,0,img);
            for(int i=0;i<p.length;i++){
                p[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

    }
}


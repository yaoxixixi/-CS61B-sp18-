public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static double g=6.67e-11;
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p) {
        double x;
        double y;
        x = this.xxPos - p.xxPos;
        y = this.yyPos - p.yyPos;
        x = x * x;
        y = y * y;
        return Math.pow(x + y,0.5);

    }
    public double calcForceExertedBy(Planet p){
        double f;
        double r2;
        r2=calcDistance(p);
        f=g*this.mass*p.mass/(r2*r2);
        return f;
    }
    public double calcForceExertedByX(Planet p){
        double f;
        double fx;
        double r;
        r=this.calcDistance(p);

        f=this.calcForceExertedBy(p);

        fx=f*(p.xxPos-this.xxPos)/r;
        return fx;
    }
    public double calcForceExertedByY(Planet p){
        double f;
        double fy;
        double r;
        r=this.calcDistance(p);
        f=this.calcForceExertedBy(p);
        fy=f*(p.yyPos-this.yyPos)/r;
        return fy;
    }
    public double calcNetForceExertedByX(Planet[] p){
        double sum=0;
        for(int i=0;i<p.length;i++){
            if(!this.equals(p[i])){
                sum += this.calcForceExertedByX(p[i]);
            }
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double sum=0;
        for(int i=0;i<p.length;i++){
            if(!this.equals(p[i])){
                sum += this.calcForceExertedByY(p[i]);
            }
        }
        return sum;
    }
    public void update(double dt,double fx,double fy){
        double ax=0,ay=0;
        ax=fx/this.mass;
        ay=fy/this.mass;
        this.xxVel=this.xxVel+ax*dt;
        this.yyVel=this.yyVel+ay*dt;
        this.xxPos=this.xxPos+this.xxVel*dt;
        this.yyPos=this.yyPos+this.yyVel*dt;
    }
    public void draw(){
        StdDraw.picture(this.xxPos+0.5,this.yyPos+0.5,"images/"+this.imgFileName);
    }
}

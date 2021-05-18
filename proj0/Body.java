public class Body{
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static double G = 6.67e-11;
    public Body(double xP, double yP, double xV,
              double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
    }
    public double calcDistance(Body d){
        return Math.pow(Math.pow(this.xxPos-d.xxPos, 2)+Math.pow(this.yyPos-d.yyPos, 2), 0.5);
    }       
    public double calcForceExertedBy(Body f){
        return G*this.mass*f.mass/Math.pow(this.calcDistance(f), 2);
    }
    public double calcForceExertedByX(Body fx){
        double dx = fx.xxPos - this.xxPos;
        return this.calcForceExertedBy(fx)*dx/this.calcDistance(fx);
    }
    public double calcForceExertedByY(Body fy){
        double dy = fy.yyPos - this.yyPos;
        return this.calcForceExertedBy(fy)*dy/this.calcDistance(fy);
    }

    public double calcNetForceExertedByX(Body[] nfx){
        double result = 0;
        for (Body s : nfx) {
            if (s.equals(this))
                continue;
            result += this.calcForceExertedByX(s);
        }
        return result;
     }

    public double calcNetForceExertedByY(Body[] nfy){
        double result = 0;
        for (Body s : nfy) {
            if (s.equals(this))
                continue;
            result += this.calcForceExertedByY(s);
        }
        return result;
     }

    public void update(double dt, double fX, double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel += dt*ax;
        this.yyVel += dt*ay;
        this.xxPos += dt*this.xxVel;
        this.yyPos += dt*this.yyVel;
    }

    public void draw() {
        String filename = this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, "/images/"+filename);
    }

}
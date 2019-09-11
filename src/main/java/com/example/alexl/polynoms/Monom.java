package com.example.alexl.polynoms;
public class Monom
{
    private double coeff;
    private int exp;

    public Monom(double coeff, int exp) throws MalformedMonomException
    {
        if(coeff==0 || exp<0)
            throw new MalformedMonomException();
        else
        {
            this.coeff=coeff;
            this.exp=exp;
        }
    }
    public double getCoeff()
    {
        return this.coeff;
    }
    public int getExp()
    {
        return this.exp;
    }
    public String toString()
    {
        String str="";
        if((this.getCoeff()==1||this.getCoeff()==-1)&&this.getExp()==0) //for 1 and -1
            str+=this.getCoeff();

        else if(this.getCoeff()!=1)
        {
            if(this.getCoeff()==-1)
                str+="-";
            else
                str+=this.getCoeff();
        }
        if(this.getExp()!=0)
            str+="x";

        if(this.getExp()!=1 && this.getExp()!=0) //for the regular monom like 2x^3
            str+="^"+this.getExp();

        return str;
    }
    public double evaluate(double x){
        return this.getCoeff()*Math.pow(x, this.getExp());
    }

    public static Monom parseMonom(String s) throws MalformedMonomException //will insert parts of the string to another string and will check if the cutted strings are equal to a valid monom. (valid exp and valid coeff)
    {
        double coeff=1;
        int exp=0;
        String in="";
        s=s.replaceAll("\\s",""); //remove blank spaces
        try{
            if(s.charAt(0)=='-')//for negative numbers set coiff to negative
            {
                coeff=-1;
                s=s.substring(1);
            }
            if(s.contains(""+'.')) //like 23.6
            {
                while(s.length()!=0 && s.charAt(0)!='x' && s.charAt(0)!='X')
                {
                    in+=s.charAt(0);
                    s=s.substring(1);
                }
                coeff*=Double.parseDouble(in);
                in="";
            }
            if(s.contains(""+'x')||s.contains(""+'X'))
            {
                while(s.charAt(0)!='x' && s.charAt(0)!='X')
                {
                    in+=s.charAt(0);
                    s=s.substring(1);
                }
                if(in.length()!=0)
                    coeff*=Integer.parseInt(in);
                exp=1;
                s=s.substring(1);
                in="";
            }
            if(s.length()!=0 && s.charAt(0)=='^')  //if has exp
            {
                s=s.substring(1);
                while(s.length()!=0)
                {
                    in+=s.charAt(0);
                    s=s.substring(1);
                }
                exp=Integer.parseInt(in);
                in="";
            }
            if(s.length()==0)
                return new Monom(coeff, exp);
            else //if monom is only with number
            {
                while(s.length()!=0)
                {
                    in+=s.charAt(0);
                    s=s.substring(1);
                }
                coeff*=Integer.parseInt(in);
            }
            return new Monom(coeff, exp);
        }
        catch(Exception e)
        {
            throw new MalformedMonomException();
        }
    }
}
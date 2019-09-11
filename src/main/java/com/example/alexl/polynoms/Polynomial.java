package com.example.alexl.polynoms;
import java.util.Vector;
public class Polynomial
{
    Vector<Monom> monoms;
    public Polynomial()
    {
        this.monoms=new Vector<Monom>();
    }

    public String toString() //will use a copy vector of polynoms to print the values
    {
        Vector<Monom> copy=new Vector<>();
        for(int i=0;i<this.monoms.size();i++) //copy data to temp vector
            copy.add(this.monoms.get(i));

        String str="";
        int spot=0;
        while(copy.size()>0)
        {
            Monom min=copy.get(0);
            for(int i=0;i<copy.size();i++)//find the right place to place the polynom in the string
            {
                if(copy.get(i).getExp()<min.getExp())
                {
                    min=copy.get(i);
                    spot=i;
                }
            }//for different cases of polynoms to draw not as usual. like x -x 1 -1
            if(str.equals(""))
                str+=min.toString();
            else if(copy.size()>1 && min.getCoeff()>0)
                str+="+"+min.toString();
            else if(min.getCoeff()<0)
                str+=min.toString();
            else if(copy.size()==0 && min.getCoeff()<0)
                str+=min.toString();
            else
                str+="+"+min.toString();
            copy.remove(spot);
            spot=0;
        }
        return str;
    }
    public double evaluate(double x)
    {
        double sum=0;
        for(int i=0;i<monoms.size();i++)//make the sum of all monoms
            sum+=monoms.get(i).evaluate(x);
        return sum;
    }

    public void addMonom(Monom m) throws MalformedMonomException
    {
        for(int i=0;i<monoms.size();i++)
        {
            if(this.monoms.get(i).getExp() == m.getExp()) //if same exp exists
            {
                if(this.monoms.get(i).getCoeff()+m.getCoeff()==0) //if sum makes 0 -remove
                {
                    this.monoms.remove(i);
                    return;
                }
                Monom p=new Monom(this.monoms.get(i).getCoeff()+m.getCoeff(),this.monoms.get(i).getExp()); // add the sum like x+x =2X or 1+3=4
                this.monoms.add(p);
                this.monoms.remove(i);
                return;
            }
        }
        this.monoms.add(m); //if not the above
    }
    public static Polynomial parsePolynomial(String s) throws MalformedPolynomialException //will separate the string to parts and will converted each cutted string to a monom.
    {
        Polynomial p=new Polynomial();
        s=s.replaceAll("\\s",""); //remove spaces
        String str="";
        if(s.length()==0)
            throw new MalformedPolynomialException();
        if(s.charAt(0)=='-'&&s.length()==1)
            throw new MalformedPolynomialException();
        if(s.charAt(0)=='-') //for negative polynoms
        {
            str+='-';
            s=s.substring(1);
        }
        while(s.length()!=0)//str will try to hold the monom to add
        {
            while(s.length()!=0)
            {
                if(s.charAt(0)=='+'||s.charAt(0)=='-')
                    break;
                str+=s.charAt(0);
                s=s.substring(1);
            }
            try
            {
                p.addMonom(Monom.parseMonom(str));
            }
            catch (MalformedMonomException e)
            {
                throw new MalformedPolynomialException();
            }
            str="";
            if(s.length()==0)
                return p;
            if(s.length()!=0&&s.charAt(0)=='+')
                s=s.substring(1);

            else if(s.length()!=0&&s.charAt(0)=='-')
            {
                str+='-';
                s=s.substring(1);
            }
            else
                throw new MalformedPolynomialException();
        }
        return p;
    }


}


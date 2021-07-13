/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Hibernate.NewHibernateUtil;
import POJO.Kardex;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author Samir
 */
public class Reporte_VIHController implements Initializable {
    
     SessionFactory factor;
     Session s =  NewHibernateUtil.getSessionFactory().openSession();
    @FXML TextField e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16,e17,e18,e19,e20,e21,e22,e23,e24,e25,e26;
@FXML TextField f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21,f22,f23,f24,f25,f26;
@FXML TextField m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19,m20,m21,m22,m23,m24,m25,m26;
@FXML TextField a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26;
@FXML TextField mm1,mm2,mm3,mm4,mm5,mm6,mm7,mm8,mm9,mm10,mm11,mm12,mm13,mm14,mm15,mm16,mm17,mm18,mm19,mm20,mm21,mm22,mm23,mm24,mm25,mm26;
@FXML TextField j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,j16,j17,j18,j19,j20,j21,j22,j23,j24,j25,j26;
@FXML TextField jj1,jj2,jj3,jj4,jj5,jj6,jj7,jj8,jj9,jj10,jj11,jj12,jj13,jj14,jj15,jj16,jj17,jj18,jj19,jj20,jj21,jj22,jj23,jj24,jj25,jj26;
@FXML TextField ag1,ag2,ag3,ag4,ag5,ag6,ag7,ag8,ag9,ag10,ag11,ag12,ag13,ag14,ag15,ag16,ag17,ag18,ag19,ag20,ag21,ag22,ag23,ag24,ag25,ag26;
@FXML TextField s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26;
@FXML TextField o1,o2,o3,o4,o5,o6,o7,o8,o9,o10,o11,o12,o13,o14,o15,o16,o17,o18,o19,o20,o21,o22,o23,o24,o25,o26;
@FXML TextField n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19,n20,n21,n22,n23,n24,n25,n26;
@FXML TextField d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26;
@FXML TextField z1,z2,z3,z4,z5,z6,z7,z8,z9,z10,z11,z12,z13,z14,z15,z16,z17,z18,z19,z20,z21,z22,z23,z24,z25,z26; 

    @FXML Button can1,can2,can3,can4,can5,can6,can7,can8,can9,can10,can11,can12;
    @FXML Button save;

//    Connection connMY = null;
    public void listar(){

        Query query = s.createQuery("        select new map (k.ia1,k.ia2,k.ca1,k.ca2,k.ea1,k.ea2,k.ea1r,k.ea1nr,k.ea2r,k.ea2nr,k.pa1r,k.pa1nr,\n" +
        "        k.pa2r,k.pa2nr,k.ta1r,k.ta1nr,k.ta2r,k.ta2nr,k.xa1r,k.xa1nr,k.xa2r,k.xa2nr,k.cca1,k.cca2,\n" +
        "        k.npa1,k.npa2)\n" +
        "        from Kardex as k");
        List <Map> lista = query.list();
        System.out.println(lista.get(0));
        Iterator<String> it=lista.get(0).keySet().iterator();
        e23.setText(lista.get(0).get(it.next()).toString());
        e24.setText(lista.get(0).get(it.next()).toString());
        e25.setText(lista.get(0).get(it.next()).toString());
        e26.setText(lista.get(0).get(it.next()).toString());
        e11.setText(lista.get(0).get(it.next()).toString());
        e12.setText(lista.get(0).get(it.next()).toString());
        e13.setText(lista.get(0).get(it.next()).toString());
        e14.setText(lista.get(0).get(it.next()).toString());
        e15.setText(lista.get(0).get(it.next()).toString());
        e16.setText(lista.get(0).get(it.next()).toString());
        e17.setText(lista.get(0).get(it.next()).toString());
        e18.setText(lista.get(0).get(it.next()).toString());
        e19.setText(lista.get(0).get(it.next()).toString());
        e20.setText(lista.get(0).get(it.next()).toString());
        e1.setText(lista.get(0).get(it.next()).toString());
        e2.setText(lista.get(0).get(it.next()).toString());
        e3.setText(lista.get(0).get(it.next()).toString());
        e4.setText(lista.get(0).get(it.next()).toString());
        e5.setText(lista.get(0).get(it.next()).toString());
        e6.setText(lista.get(0).get(it.next()).toString());
        e7.setText(lista.get(0).get(it.next()).toString());
        e8.setText(lista.get(0).get(it.next()).toString());
        e9.setText(lista.get(0).get(it.next()).toString());
        e10.setText(lista.get(0).get(it.next()).toString());
        e21.setText(lista.get(0).get(it.next()).toString());
        e22.setText(lista.get(0).get(it.next()).toString());
        
        Iterator<String> it2=lista.get(1).keySet().iterator();
        f23.setText(lista.get(1).get(it2.next()).toString());
        f24.setText(lista.get(1).get(it2.next()).toString());
        f25.setText(lista.get(1).get(it2.next()).toString());
        f26.setText(lista.get(1).get(it2.next()).toString());
        f11.setText(lista.get(1).get(it2.next()).toString());
        f12.setText(lista.get(1).get(it2.next()).toString());
        f13.setText(lista.get(1).get(it2.next()).toString());
        f14.setText(lista.get(1).get(it2.next()).toString());
        f15.setText(lista.get(1).get(it2.next()).toString());
        f16.setText(lista.get(1).get(it2.next()).toString());
        f17.setText(lista.get(1).get(it2.next()).toString());
        f18.setText(lista.get(1).get(it2.next()).toString());
        f19.setText(lista.get(1).get(it2.next()).toString());
        f20.setText(lista.get(1).get(it2.next()).toString());
        f1.setText(lista.get(1).get(it2.next()).toString());
        f2.setText(lista.get(1).get(it2.next()).toString());
        f3.setText(lista.get(1).get(it2.next()).toString());
        f4.setText(lista.get(1).get(it2.next()).toString());
        f5.setText(lista.get(1).get(it2.next()).toString());
        f6.setText(lista.get(1).get(it2.next()).toString());
        f7.setText(lista.get(1).get(it2.next()).toString());
        f8.setText(lista.get(1).get(it2.next()).toString());
        f9.setText(lista.get(1).get(it2.next()).toString());
        f10.setText(lista.get(1).get(it2.next()).toString());
        f21.setText(lista.get(1).get(it2.next()).toString());
        f22.setText(lista.get(1).get(it2.next()).toString()); 

        Iterator<String> it3=lista.get(2).keySet().iterator();
        m23.setText(lista.get(2).get(it3.next()).toString());
        m24.setText(lista.get(2).get(it3.next()).toString());
        m25.setText(lista.get(2).get(it3.next()).toString());
        m26.setText(lista.get(2).get(it3.next()).toString());
        m11.setText(lista.get(2).get(it3.next()).toString());
        m12.setText(lista.get(2).get(it3.next()).toString());
        m13.setText(lista.get(2).get(it3.next()).toString());
        m14.setText(lista.get(2).get(it3.next()).toString());
        m15.setText(lista.get(2).get(it3.next()).toString());
        m16.setText(lista.get(2).get(it3.next()).toString());
        m17.setText(lista.get(2).get(it3.next()).toString());
        m18.setText(lista.get(2).get(it3.next()).toString());
        m19.setText(lista.get(2).get(it3.next()).toString());
        m20.setText(lista.get(2).get(it3.next()).toString());
        m1.setText(lista.get(2).get(it3.next()).toString());
        m2.setText(lista.get(2).get(it3.next()).toString());
        m3.setText(lista.get(2).get(it3.next()).toString());
        m4.setText(lista.get(2).get(it3.next()).toString());
        m5.setText(lista.get(2).get(it3.next()).toString());
        m6.setText(lista.get(2).get(it3.next()).toString());
        m7.setText(lista.get(2).get(it3.next()).toString());
        m8.setText(lista.get(2).get(it3.next()).toString());
        m9.setText(lista.get(2).get(it3.next()).toString());
        m10.setText(lista.get(2).get(it3.next()).toString());
        m21.setText(lista.get(2).get(it3.next()).toString());
        m22.setText(lista.get(2).get(it3.next()).toString());
       
        Iterator<String> it4=lista.get(3).keySet().iterator();
        a23.setText(lista.get(3).get(it4.next()).toString());
        a24.setText(lista.get(3).get(it4.next()).toString());
        a25.setText(lista.get(3).get(it4.next()).toString());
        a26.setText(lista.get(3).get(it4.next()).toString());
        a11.setText(lista.get(3).get(it4.next()).toString());
        a12.setText(lista.get(3).get(it4.next()).toString());
        a13.setText(lista.get(3).get(it4.next()).toString());
        a14.setText(lista.get(3).get(it4.next()).toString());
        a15.setText(lista.get(3).get(it4.next()).toString());
        a16.setText(lista.get(3).get(it4.next()).toString());
        a17.setText(lista.get(3).get(it4.next()).toString());
        a18.setText(lista.get(3).get(it4.next()).toString());
        a19.setText(lista.get(3).get(it4.next()).toString());
        a20.setText(lista.get(3).get(it4.next()).toString());
        a1.setText(lista.get(3).get(it4.next()).toString());
        a2.setText(lista.get(3).get(it4.next()).toString());
        a3.setText(lista.get(3).get(it4.next()).toString());
        a4.setText(lista.get(3).get(it4.next()).toString());
        a5.setText(lista.get(3).get(it4.next()).toString());
        a6.setText(lista.get(3).get(it4.next()).toString());
        a7.setText(lista.get(3).get(it4.next()).toString());
        a8.setText(lista.get(3).get(it4.next()).toString());
        a9.setText(lista.get(3).get(it4.next()).toString());
        a10.setText(lista.get(3).get(it4.next()).toString());
        a21.setText(lista.get(3).get(it4.next()).toString());
        a22.setText(lista.get(3).get(it4.next()).toString());


        Iterator<String> it5=lista.get(4).keySet().iterator();
        mm23.setText(lista.get(4).get(it5.next()).toString());
        mm24.setText(lista.get(4).get(it5.next()).toString());
        mm25.setText(lista.get(4).get(it5.next()).toString());
        mm26.setText(lista.get(4).get(it5.next()).toString());
        mm11.setText(lista.get(4).get(it5.next()).toString());
        mm12.setText(lista.get(4).get(it5.next()).toString());
        mm13.setText(lista.get(4).get(it5.next()).toString());
        mm14.setText(lista.get(4).get(it5.next()).toString());
        mm15.setText(lista.get(4).get(it5.next()).toString());
        mm16.setText(lista.get(4).get(it5.next()).toString());
        mm17.setText(lista.get(4).get(it5.next()).toString());
        mm18.setText(lista.get(4).get(it5.next()).toString());
        mm19.setText(lista.get(4).get(it5.next()).toString());
        mm20.setText(lista.get(4).get(it5.next()).toString());
        mm1.setText(lista.get(4).get(it5.next()).toString());
        mm2.setText(lista.get(4).get(it5.next()).toString());
        mm3.setText(lista.get(4).get(it5.next()).toString());
        mm4.setText(lista.get(4).get(it5.next()).toString());
        mm5.setText(lista.get(4).get(it5.next()).toString());
        mm6.setText(lista.get(4).get(it5.next()).toString());
        mm7.setText(lista.get(4).get(it5.next()).toString());
        mm8.setText(lista.get(4).get(it5.next()).toString());
        mm9.setText(lista.get(4).get(it5.next()).toString());
        mm10.setText(lista.get(4).get(it5.next()).toString());
        mm21.setText(lista.get(4).get(it5.next()).toString());
        mm22.setText(lista.get(4).get(it5.next()).toString());
        
        Iterator<String> it6=lista.get(5).keySet().iterator();
        j23.setText(lista.get(5).get(it6.next()).toString());
        j24.setText(lista.get(5).get(it6.next()).toString());
        j25.setText(lista.get(5).get(it6.next()).toString());
        j26.setText(lista.get(5).get(it6.next()).toString());
        j11.setText(lista.get(5).get(it6.next()).toString());
        j12.setText(lista.get(5).get(it6.next()).toString());
        j13.setText(lista.get(5).get(it6.next()).toString());
        j14.setText(lista.get(5).get(it6.next()).toString());
        j15.setText(lista.get(5).get(it6.next()).toString());
        j16.setText(lista.get(5).get(it6.next()).toString());
        j17.setText(lista.get(5).get(it6.next()).toString());
        j18.setText(lista.get(5).get(it6.next()).toString());
        j19.setText(lista.get(5).get(it6.next()).toString());
        j20.setText(lista.get(5).get(it6.next()).toString());
        j1.setText(lista.get(5).get(it6.next()).toString());
        j2.setText(lista.get(5).get(it6.next()).toString());
        j3.setText(lista.get(5).get(it6.next()).toString());
        j4.setText(lista.get(5).get(it6.next()).toString());
        j5.setText(lista.get(5).get(it6.next()).toString());
        j6.setText(lista.get(5).get(it6.next()).toString());
        j7.setText(lista.get(5).get(it6.next()).toString());
        j8.setText(lista.get(5).get(it6.next()).toString());
        j9.setText(lista.get(5).get(it6.next()).toString());
        j10.setText(lista.get(5).get(it6.next()).toString());
        j21.setText(lista.get(5).get(it6.next()).toString());
        j22.setText(lista.get(5).get(it6.next()).toString());

        Iterator<String> it7=lista.get(6).keySet().iterator();
        jj23.setText(lista.get(6).get(it7.next()).toString());
        jj24.setText(lista.get(6).get(it7.next()).toString());
        jj25.setText(lista.get(6).get(it7.next()).toString());
        jj26.setText(lista.get(6).get(it7.next()).toString());
        jj11.setText(lista.get(6).get(it7.next()).toString());
        jj12.setText(lista.get(6).get(it7.next()).toString());
        jj13.setText(lista.get(6).get(it7.next()).toString());
        jj14.setText(lista.get(6).get(it7.next()).toString());
        jj15.setText(lista.get(6).get(it7.next()).toString());
        jj16.setText(lista.get(6).get(it7.next()).toString());
        jj17.setText(lista.get(6).get(it7.next()).toString());
        jj18.setText(lista.get(6).get(it7.next()).toString());
        jj19.setText(lista.get(6).get(it7.next()).toString());
        jj20.setText(lista.get(6).get(it7.next()).toString());
        jj1.setText(lista.get(6).get(it7.next()).toString());
        jj2.setText(lista.get(6).get(it7.next()).toString());
        jj3.setText(lista.get(6).get(it7.next()).toString());
        jj4.setText(lista.get(6).get(it7.next()).toString());
        jj5.setText(lista.get(6).get(it7.next()).toString());
        jj6.setText(lista.get(6).get(it7.next()).toString());
        jj7.setText(lista.get(6).get(it7.next()).toString());
        jj8.setText(lista.get(6).get(it7.next()).toString());
        jj9.setText(lista.get(6).get(it7.next()).toString());
        jj10.setText(lista.get(6).get(it7.next()).toString());
        jj21.setText(lista.get(6).get(it7.next()).toString());
        jj22.setText(lista.get(6).get(it7.next()).toString());

                Iterator<String> it8=lista.get(7).keySet().iterator();
        ag23.setText(lista.get(7).get(it8.next()).toString());
        ag24.setText(lista.get(7).get(it8.next()).toString());
        ag25.setText(lista.get(7).get(it8.next()).toString());
        ag26.setText(lista.get(7).get(it8.next()).toString());
        ag11.setText(lista.get(7).get(it8.next()).toString());
        ag12.setText(lista.get(7).get(it8.next()).toString());
        ag13.setText(lista.get(7).get(it8.next()).toString());
        ag14.setText(lista.get(7).get(it8.next()).toString());
        ag15.setText(lista.get(7).get(it8.next()).toString());
        ag16.setText(lista.get(7).get(it8.next()).toString());
        ag17.setText(lista.get(7).get(it8.next()).toString());
        ag18.setText(lista.get(7).get(it8.next()).toString());
        ag19.setText(lista.get(7).get(it8.next()).toString());
        ag20.setText(lista.get(7).get(it8.next()).toString());
        ag1.setText(lista.get(7).get(it8.next()).toString());
        ag2.setText(lista.get(7).get(it8.next()).toString());
        ag3.setText(lista.get(7).get(it8.next()).toString());
        ag4.setText(lista.get(7).get(it8.next()).toString());
        ag5.setText(lista.get(7).get(it8.next()).toString());
        ag6.setText(lista.get(7).get(it8.next()).toString());
        ag7.setText(lista.get(7).get(it8.next()).toString());
        ag8.setText(lista.get(7).get(it8.next()).toString());
        ag9.setText(lista.get(7).get(it8.next()).toString());
        ag10.setText(lista.get(7).get(it8.next()).toString());
        ag21.setText(lista.get(7).get(it8.next()).toString());
        ag22.setText(lista.get(7).get(it8.next()).toString());

        Iterator<String> it9=lista.get(8).keySet().iterator();
        s23.setText(lista.get(8).get(it9.next()).toString());
        s24.setText(lista.get(8).get(it9.next()).toString());
        s25.setText(lista.get(8).get(it9.next()).toString());
        s26.setText(lista.get(8).get(it9.next()).toString());
        s11.setText(lista.get(8).get(it9.next()).toString());
        s12.setText(lista.get(8).get(it9.next()).toString());
        s13.setText(lista.get(8).get(it9.next()).toString());
        s14.setText(lista.get(8).get(it9.next()).toString());
        s15.setText(lista.get(8).get(it9.next()).toString());
        s16.setText(lista.get(8).get(it9.next()).toString());
        s17.setText(lista.get(8).get(it9.next()).toString());
        s18.setText(lista.get(8).get(it9.next()).toString());
        s19.setText(lista.get(8).get(it9.next()).toString());
        s20.setText(lista.get(8).get(it9.next()).toString());
        s1.setText(lista.get(8).get(it9.next()).toString());
        s2.setText(lista.get(8).get(it9.next()).toString());
        s3.setText(lista.get(8).get(it9.next()).toString());
        s4.setText(lista.get(8).get(it9.next()).toString());
        s5.setText(lista.get(8).get(it9.next()).toString());
        s6.setText(lista.get(8).get(it9.next()).toString());
        s7.setText(lista.get(8).get(it9.next()).toString());
        s8.setText(lista.get(8).get(it9.next()).toString());
        s9.setText(lista.get(8).get(it9.next()).toString());
        s10.setText(lista.get(8).get(it9.next()).toString());
        s21.setText(lista.get(8).get(it9.next()).toString());
        s22.setText(lista.get(8).get(it9.next()).toString());

        
        Iterator<String> it10=lista.get(9).keySet().iterator();
        o23.setText(lista.get(9).get(it10.next()).toString());
        o24.setText(lista.get(9).get(it10.next()).toString());
        o25.setText(lista.get(9).get(it10.next()).toString());
        o26.setText(lista.get(9).get(it10.next()).toString());
        o11.setText(lista.get(9).get(it10.next()).toString());
        o12.setText(lista.get(9).get(it10.next()).toString());
        o13.setText(lista.get(9).get(it10.next()).toString());
        o14.setText(lista.get(9).get(it10.next()).toString());
        o15.setText(lista.get(9).get(it10.next()).toString());
        o16.setText(lista.get(9).get(it10.next()).toString());
        o17.setText(lista.get(9).get(it10.next()).toString());
        o18.setText(lista.get(9).get(it10.next()).toString());
        o19.setText(lista.get(9).get(it10.next()).toString());
        o20.setText(lista.get(9).get(it10.next()).toString());
        o1.setText(lista.get(9).get(it10.next()).toString());
        o2.setText(lista.get(9).get(it10.next()).toString());
        o3.setText(lista.get(9).get(it10.next()).toString());
        o4.setText(lista.get(9).get(it10.next()).toString());
        o5.setText(lista.get(9).get(it10.next()).toString());
        o6.setText(lista.get(9).get(it10.next()).toString());
        o7.setText(lista.get(9).get(it10.next()).toString());
        o8.setText(lista.get(9).get(it10.next()).toString());
        o9.setText(lista.get(9).get(it10.next()).toString());
        o10.setText(lista.get(9).get(it10.next()).toString());
        o21.setText(lista.get(9).get(it10.next()).toString());
        o22.setText(lista.get(9).get(it10.next()).toString());

        Iterator<String> it11=lista.get(10).keySet().iterator();
        n23.setText(lista.get(10).get(it11.next()).toString());
        n24.setText(lista.get(10).get(it11.next()).toString());
        n25.setText(lista.get(10).get(it11.next()).toString());
        n26.setText(lista.get(10).get(it11.next()).toString());
        n11.setText(lista.get(10).get(it11.next()).toString());
        n12.setText(lista.get(10).get(it11.next()).toString());
        n13.setText(lista.get(10).get(it11.next()).toString());
        n14.setText(lista.get(10).get(it11.next()).toString());
        n15.setText(lista.get(10).get(it11.next()).toString());
        n16.setText(lista.get(10).get(it11.next()).toString());
        n17.setText(lista.get(10).get(it11.next()).toString());
        n18.setText(lista.get(10).get(it11.next()).toString());
        n19.setText(lista.get(10).get(it11.next()).toString());
        n20.setText(lista.get(10).get(it11.next()).toString());
        n1.setText(lista.get(10).get(it11.next()).toString());
        n2.setText(lista.get(10).get(it11.next()).toString());
        n3.setText(lista.get(10).get(it11.next()).toString());
        n4.setText(lista.get(10).get(it11.next()).toString());
        n5.setText(lista.get(10).get(it11.next()).toString());
        n6.setText(lista.get(10).get(it11.next()).toString());
        n7.setText(lista.get(10).get(it11.next()).toString());
        n8.setText(lista.get(10).get(it11.next()).toString());
        n9.setText(lista.get(10).get(it11.next()).toString());
        n10.setText(lista.get(10).get(it11.next()).toString());
        n21.setText(lista.get(10).get(it11.next()).toString());
        n22.setText(lista.get(10).get(it11.next()).toString());

        Iterator<String> it12=lista.get(11).keySet().iterator();
        d23.setText(lista.get(11).get(it12.next()).toString());
        d24.setText(lista.get(11).get(it12.next()).toString());
        d25.setText(lista.get(11).get(it12.next()).toString());
        d26.setText(lista.get(11).get(it12.next()).toString());
        d11.setText(lista.get(11).get(it12.next()).toString());
        d12.setText(lista.get(11).get(it12.next()).toString());
        d13.setText(lista.get(11).get(it12.next()).toString());
        d14.setText(lista.get(11).get(it12.next()).toString());
        d15.setText(lista.get(11).get(it12.next()).toString());
        d16.setText(lista.get(11).get(it12.next()).toString());
        d17.setText(lista.get(11).get(it12.next()).toString());
        d18.setText(lista.get(11).get(it12.next()).toString());
        d19.setText(lista.get(11).get(it12.next()).toString());
        d20.setText(lista.get(11).get(it12.next()).toString());
        d1.setText(lista.get(11).get(it12.next()).toString());
        d2.setText(lista.get(11).get(it12.next()).toString());
        d3.setText(lista.get(11).get(it12.next()).toString());
        d4.setText(lista.get(11).get(it12.next()).toString());
        d5.setText(lista.get(11).get(it12.next()).toString());
        d6.setText(lista.get(11).get(it12.next()).toString());
        d7.setText(lista.get(11).get(it12.next()).toString());
        d8.setText(lista.get(11).get(it12.next()).toString());
        d9.setText(lista.get(11).get(it12.next()).toString());
        d10.setText(lista.get(11).get(it12.next()).toString());
        d21.setText(lista.get(11).get(it12.next()).toString());
        d22.setText(lista.get(11).get(it12.next()).toString());

        Iterator<String> it13=lista.get(12).keySet().iterator();
        z23.setText(lista.get(12).get(it13.next()).toString());
        z24.setText(lista.get(12).get(it13.next()).toString());
        z25.setText(lista.get(12).get(it13.next()).toString());
        z26.setText(lista.get(12).get(it13.next()).toString());
        z11.setText(lista.get(12).get(it13.next()).toString());
        z12.setText(lista.get(12).get(it13.next()).toString());
        z13.setText(lista.get(12).get(it13.next()).toString());
        z14.setText(lista.get(12).get(it13.next()).toString());
        z15.setText(lista.get(12).get(it13.next()).toString());
        z16.setText(lista.get(12).get(it13.next()).toString());
        z17.setText(lista.get(12).get(it13.next()).toString());
        z18.setText(lista.get(12).get(it13.next()).toString());
        z19.setText(lista.get(12).get(it13.next()).toString());
        z20.setText(lista.get(12).get(it13.next()).toString());
        z1.setText(lista.get(12).get(it13.next()).toString());
        z2.setText(lista.get(12).get(it13.next()).toString());
        z3.setText(lista.get(12).get(it13.next()).toString());
        z4.setText(lista.get(12).get(it13.next()).toString());
        z5.setText(lista.get(12).get(it13.next()).toString());
        z6.setText(lista.get(12).get(it13.next()).toString());
        z7.setText(lista.get(12).get(it13.next()).toString());
        z8.setText(lista.get(12).get(it13.next()).toString());
        z9.setText(lista.get(12).get(it13.next()).toString());
        z10.setText(lista.get(12).get(it13.next()).toString());
        z21.setText(lista.get(12).get(it13.next()).toString());
        z22.setText(lista.get(12).get(it13.next()).toString());
        
    }
    
    @FXML public void can1(){
    e1.setText("0");
    e2.setText("0");
    e3.setText("0");
    e4.setText("0");
    e5.setText("0");
    e6.setText("0");
    e7.setText("0");
    e8.setText("0");
    e9.setText("0");
    e10.setText("0");
    e11.setText("0");
    e12.setText("0");
    e13.setText("0");
    e14.setText("0");
    e15.setText("0");
    e16.setText("0");
    e17.setText("0");
    e18.setText("0");
    e19.setText("0");
    e20.setText("0");
    e21.setText("0");
    e22.setText("0");
    e23.setText("0");
    e24.setText("0");
    e25.setText("0");
    e26.setText("0");     
    } 
    
    @FXML public void can2(){
    f1.setText("0");
    f2.setText("0");
    f3.setText("0");
    f4.setText("0");
    f5.setText("0");
    f6.setText("0");
    f7.setText("0");
    f8.setText("0");
    f9.setText("0");
    f10.setText("0");
    f11.setText("0");
    f12.setText("0");
    f13.setText("0");
    f14.setText("0");
    f15.setText("0");
    f16.setText("0");
    f17.setText("0");
    f18.setText("0");
    f19.setText("0");
    f20.setText("0");
    f21.setText("0");
    f22.setText("0");
    f23.setText("0");
    f24.setText("0");
    f25.setText("0");
    f26.setText("0");     
    }
    @FXML public void can3(){
    m1.setText("0");
    m2.setText("0");
    m3.setText("0");
    m4.setText("0");
    m5.setText("0");
    m6.setText("0");
    m7.setText("0");
    m8.setText("0");
    m9.setText("0");
    m10.setText("0");
    m11.setText("0");
    m12.setText("0");
    m13.setText("0");
    m14.setText("0");
    m15.setText("0");
    m16.setText("0");
    m17.setText("0");
    m18.setText("0");
    m19.setText("0");
    m20.setText("0");
    m21.setText("0");
    m22.setText("0");
    m23.setText("0");
    m24.setText("0");
    m25.setText("0");
    m26.setText("0");     
    }
    
    @FXML public void can4(){
    a1.setText("0");
    a2.setText("0");
    a3.setText("0");
    a4.setText("0");
    a5.setText("0");
    a6.setText("0");
    a7.setText("0");
    a8.setText("0");
    a9.setText("0");
    a10.setText("0");
    a11.setText("0");
    a12.setText("0");
    a13.setText("0");
    a14.setText("0");
    a15.setText("0");
    a16.setText("0");
    a17.setText("0");
    a18.setText("0");
    a19.setText("0");
    a20.setText("0");
    a21.setText("0");
    a22.setText("0");
    a23.setText("0");
    a24.setText("0");
    a25.setText("0");
    a26.setText("0");     
    }
    
    @FXML public void can5(){
    mm1.setText("0");
    mm2.setText("0");
    mm3.setText("0");
    mm4.setText("0");
    mm5.setText("0");
    mm6.setText("0");
    mm7.setText("0");
    mm8.setText("0");
    mm9.setText("0");
    mm10.setText("0");
    mm11.setText("0");
    mm12.setText("0");
    mm13.setText("0");
    mm14.setText("0");
    mm15.setText("0");
    mm16.setText("0");
    mm17.setText("0");
    mm18.setText("0");
    mm19.setText("0");
    mm20.setText("0");
    mm21.setText("0");
    mm22.setText("0");
    mm23.setText("0");
    mm24.setText("0");
    mm25.setText("0");
    mm26.setText("0");     
    }    
    @FXML public void can6(){
    j1.setText("0");
    j2.setText("0");
    j3.setText("0");
    j4.setText("0");
    j5.setText("0");
    j6.setText("0");
    j7.setText("0");
    j8.setText("0");
    j9.setText("0");
    j10.setText("0");
    j11.setText("0");
    j12.setText("0");
    j13.setText("0");
    j14.setText("0");
    j15.setText("0");
    j16.setText("0");
    j17.setText("0");
    j18.setText("0");
    j19.setText("0");
    j20.setText("0");
    j21.setText("0");
    j22.setText("0");
    j23.setText("0");
    j24.setText("0");
    j25.setText("0");
    j26.setText("0");     
    }    
    @FXML public void can7(){
    jj1.setText("0");
    jj2.setText("0");
    jj3.setText("0");
    jj4.setText("0");
    jj5.setText("0");
    jj6.setText("0");
    jj7.setText("0");
    jj8.setText("0");
    jj9.setText("0");
    jj10.setText("0");
    jj11.setText("0");
    jj12.setText("0");
    jj13.setText("0");
    jj14.setText("0");
    jj15.setText("0");
    jj16.setText("0");
    jj17.setText("0");
    jj18.setText("0");
    jj19.setText("0");
    jj20.setText("0");
    jj21.setText("0");
    jj22.setText("0");
    jj23.setText("0");
    jj24.setText("0");
    jj25.setText("0");
    jj26.setText("0");     
    }
   @FXML public void can8(){
    ag1.setText("0");
    ag2.setText("0");
    ag3.setText("0");
    ag4.setText("0");
    ag5.setText("0");
    ag6.setText("0");
    ag7.setText("0");
    ag8.setText("0");
    ag9.setText("0");
    ag10.setText("0");
    ag11.setText("0");
    ag12.setText("0");
    ag13.setText("0");
    ag14.setText("0");
    ag15.setText("0");
    ag16.setText("0");
    ag17.setText("0");
    ag18.setText("0");
    ag19.setText("0");
    ag20.setText("0");
    ag21.setText("0");
    ag22.setText("0");
    ag23.setText("0");
    ag24.setText("0");
    ag25.setText("0");
    ag26.setText("0");     
    } 
    @FXML public void can9(){
    s1.setText("0");
    s2.setText("0");
    s3.setText("0");
    s4.setText("0");
    s5.setText("0");
    s6.setText("0");
    s7.setText("0");
    s8.setText("0");
    s9.setText("0");
    s10.setText("0");
    s11.setText("0");
    s12.setText("0");
    s13.setText("0");
    s14.setText("0");
    s15.setText("0");
    s16.setText("0");
    s17.setText("0");
    s18.setText("0");
    s19.setText("0");
    s20.setText("0");
    s21.setText("0");
    s22.setText("0");
    s23.setText("0");
    s24.setText("0");
    s25.setText("0");
    s26.setText("0");     
    }        // TODO
    @FXML public void can10(){
    o1.setText("0");
    o2.setText("0");
    o3.setText("0");
    o4.setText("0");
    o5.setText("0");
    o6.setText("0");
    o7.setText("0");
    o8.setText("0");
    o9.setText("0");
    o10.setText("0");
    o11.setText("0");
    o12.setText("0");
    o13.setText("0");
    o14.setText("0");
    o15.setText("0");
    o16.setText("0");
    o17.setText("0");
    o18.setText("0");
    o19.setText("0");
    o20.setText("0");
    o21.setText("0");
    o22.setText("0");
    o23.setText("0");
    o24.setText("0");
    o25.setText("0");
    o26.setText("0");     
    }        
    @FXML public void can11(){
    n1.setText("0");
    n2.setText("0");
    n3.setText("0");
    n4.setText("0");
    n5.setText("0");
    n6.setText("0");
    n7.setText("0");
    n8.setText("0");
    n9.setText("0");
    n10.setText("0");
    n11.setText("0");
    n12.setText("0");
    n13.setText("0");
    n14.setText("0");
    n15.setText("0");
    n16.setText("0");
    n17.setText("0");
    n18.setText("0");
    n19.setText("0");
    n20.setText("0");
    n21.setText("0");
    n22.setText("0");
    n23.setText("0");
    n24.setText("0");
    n25.setText("0");
    n26.setText("0");     
    }    
    @FXML public void can12(){
    d1.setText("0");
    d2.setText("0");
    d3.setText("0");
    d4.setText("0");
    d5.setText("0");
    d6.setText("0");
    d7.setText("0");
    d8.setText("0");
    d9.setText("0");
    d10.setText("0");
    d11.setText("0");
    d12.setText("0");
    d13.setText("0");
    d14.setText("0");
    d15.setText("0");
    d16.setText("0");
    d17.setText("0");
    d18.setText("0");
    d19.setText("0");
    d20.setText("0");
    d21.setText("0");
    d22.setText("0");
    d23.setText("0");
    d24.setText("0");
    d25.setText("0");
    d26.setText("0");     
    }
    
    
    @FXML public void save(){
        
    s.getTransaction().begin();    
    Query query1=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=1");
    query1.setParameter("v1", Integer.parseInt(e1.getText()));
    query1.setParameter("v2", Integer.parseInt(e2.getText()));
    query1.setParameter("v3", Integer.parseInt(e3.getText()));
    query1.setParameter("v4", Integer.parseInt(e4.getText()));
    query1.setParameter("v5", Integer.parseInt(e5.getText()));
    query1.setParameter("v6", Integer.parseInt(e6.getText()));
    query1.setParameter("v7", Integer.parseInt(e7.getText()));
    query1.setParameter("v8", Integer.parseInt(e8.getText()));
    query1.setParameter("v9", Integer.parseInt(e9.getText()));
    query1.setParameter("v10",Integer.parseInt(e10.getText()));
    query1.setParameter("v11",Integer.parseInt(e11.getText()));
    query1.setParameter("v12",Integer.parseInt(e12.getText()));
    query1.setParameter("v13",Integer.parseInt(e13.getText()));
    query1.setParameter("v14",Integer.parseInt(e14.getText()));
    query1.setParameter("v15",Integer.parseInt(e15.getText()));
    query1.setParameter("v16",Integer.parseInt(e16.getText()));
    query1.setParameter("v17",Integer.parseInt(e17.getText()));
    query1.setParameter("v18",Integer.parseInt(e18.getText()));
    query1.setParameter("v19",Integer.parseInt(e19.getText()));
    query1.setParameter("v20",Integer.parseInt(e20.getText()));
    query1.setParameter("v21",Integer.parseInt(e21.getText()));
    query1.setParameter("v22",Integer.parseInt(e22.getText()));
    query1.setParameter("v23",Integer.parseInt(e23.getText()));
    query1.setParameter("v24",Integer.parseInt(e24.getText()));
    query1.setParameter("v25",Integer.parseInt(e25.getText()));
    query1.setParameter("v26",Integer.parseInt(e26.getText()));
    query1.executeUpdate();
    s.getTransaction().commit();
 
    s.getTransaction().begin();    
    Query query2=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=2");
    query2.setParameter("v1", Integer.parseInt(f1.getText()));
    query2.setParameter("v2", Integer.parseInt(f2.getText()));
    query2.setParameter("v3", Integer.parseInt(f3.getText()));
    query2.setParameter("v4", Integer.parseInt(f4.getText()));
    query2.setParameter("v5", Integer.parseInt(f5.getText()));
    query2.setParameter("v6", Integer.parseInt(f6.getText()));
    query2.setParameter("v7", Integer.parseInt(f7.getText()));
    query2.setParameter("v8", Integer.parseInt(f8.getText()));
    query2.setParameter("v9", Integer.parseInt(f9.getText()));
    query2.setParameter("v10",Integer.parseInt(f10.getText()));
    query2.setParameter("v11",Integer.parseInt(f11.getText()));
    query2.setParameter("v12",Integer.parseInt(f12.getText()));
    query2.setParameter("v13",Integer.parseInt(f13.getText()));
    query2.setParameter("v14",Integer.parseInt(f14.getText()));
    query2.setParameter("v15",Integer.parseInt(f15.getText()));
    query2.setParameter("v16",Integer.parseInt(f16.getText()));
    query2.setParameter("v17",Integer.parseInt(f17.getText()));
    query2.setParameter("v18",Integer.parseInt(f18.getText()));
    query2.setParameter("v19",Integer.parseInt(f19.getText()));
    query2.setParameter("v20",Integer.parseInt(f20.getText()));
    query2.setParameter("v21",Integer.parseInt(f21.getText()));
    query2.setParameter("v22",Integer.parseInt(f22.getText()));
    query2.setParameter("v23",Integer.parseInt(f23.getText()));
    query2.setParameter("v24",Integer.parseInt(f24.getText()));
    query2.setParameter("v25",Integer.parseInt(f25.getText()));
    query2.setParameter("v26",Integer.parseInt(f26.getText()));
    query2.executeUpdate();
    s.getTransaction().commit();    
    
    s.getTransaction().begin();    
    Query query3=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=3");
    query3.setParameter("v1", Integer.parseInt(m1.getText()));
    query3.setParameter("v2", Integer.parseInt(m2.getText()));
    query3.setParameter("v3", Integer.parseInt(m3.getText()));
    query3.setParameter("v4", Integer.parseInt(m4.getText()));
    query3.setParameter("v5", Integer.parseInt(m5.getText()));
    query3.setParameter("v6", Integer.parseInt(m6.getText()));
    query3.setParameter("v7", Integer.parseInt(m7.getText()));
    query3.setParameter("v8", Integer.parseInt(m8.getText()));
    query3.setParameter("v9", Integer.parseInt(m9.getText()));
    query3.setParameter("v10",Integer.parseInt(m10.getText()));
    query3.setParameter("v11",Integer.parseInt(m11.getText()));
    query3.setParameter("v12",Integer.parseInt(m12.getText()));
    query3.setParameter("v13",Integer.parseInt(m13.getText()));
    query3.setParameter("v14",Integer.parseInt(m14.getText()));
    query3.setParameter("v15",Integer.parseInt(m15.getText()));
    query3.setParameter("v16",Integer.parseInt(m16.getText()));
    query3.setParameter("v17",Integer.parseInt(m17.getText()));
    query3.setParameter("v18",Integer.parseInt(m18.getText()));
    query3.setParameter("v19",Integer.parseInt(m19.getText()));
    query3.setParameter("v20",Integer.parseInt(m20.getText()));
    query3.setParameter("v21",Integer.parseInt(m21.getText()));
    query3.setParameter("v22",Integer.parseInt(m22.getText()));
    query3.setParameter("v23",Integer.parseInt(m23.getText()));
    query3.setParameter("v24",Integer.parseInt(m24.getText()));
    query3.setParameter("v25",Integer.parseInt(m25.getText()));
    query3.setParameter("v26",Integer.parseInt(m26.getText()));
    query3.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query4=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=4");
    query4.setParameter("v1", Integer.parseInt(a1.getText()));
    query4.setParameter("v2", Integer.parseInt(a2.getText()));
    query4.setParameter("v3", Integer.parseInt(a3.getText()));
    query4.setParameter("v4", Integer.parseInt(a4.getText()));
    query4.setParameter("v5", Integer.parseInt(a5.getText()));
    query4.setParameter("v6", Integer.parseInt(a6.getText()));
    query4.setParameter("v7", Integer.parseInt(a7.getText()));
    query4.setParameter("v8", Integer.parseInt(a8.getText()));
    query4.setParameter("v9", Integer.parseInt(a9.getText()));
    query4.setParameter("v10",Integer.parseInt(a10.getText()));
    query4.setParameter("v11",Integer.parseInt(a11.getText()));
    query4.setParameter("v12",Integer.parseInt(a12.getText()));
    query4.setParameter("v13",Integer.parseInt(a13.getText()));
    query4.setParameter("v14",Integer.parseInt(a14.getText()));
    query4.setParameter("v15",Integer.parseInt(a15.getText()));
    query4.setParameter("v16",Integer.parseInt(a16.getText()));
    query4.setParameter("v17",Integer.parseInt(a17.getText()));
    query4.setParameter("v18",Integer.parseInt(a18.getText()));
    query4.setParameter("v19",Integer.parseInt(a19.getText()));
    query4.setParameter("v20",Integer.parseInt(a20.getText()));
    query4.setParameter("v21",Integer.parseInt(a21.getText()));
    query4.setParameter("v22",Integer.parseInt(a22.getText()));
    query4.setParameter("v23",Integer.parseInt(a23.getText()));
    query4.setParameter("v24",Integer.parseInt(a24.getText()));
    query4.setParameter("v25",Integer.parseInt(a25.getText()));
    query4.setParameter("v26",Integer.parseInt(a26.getText()));
    query4.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query5=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=5");
    query5.setParameter("v1", Integer.parseInt(mm1.getText()));
    query5.setParameter("v2", Integer.parseInt(mm2.getText()));
    query5.setParameter("v3", Integer.parseInt(mm3.getText()));
    query5.setParameter("v4", Integer.parseInt(mm4.getText()));
    query5.setParameter("v5", Integer.parseInt(mm5.getText()));
    query5.setParameter("v6", Integer.parseInt(mm6.getText()));
    query5.setParameter("v7", Integer.parseInt(mm7.getText()));
    query5.setParameter("v8", Integer.parseInt(mm8.getText()));
    query5.setParameter("v9", Integer.parseInt(mm9.getText()));
    query5.setParameter("v10",Integer.parseInt(mm10.getText()));
    query5.setParameter("v11",Integer.parseInt(mm11.getText()));
    query5.setParameter("v12",Integer.parseInt(mm12.getText()));
    query5.setParameter("v13",Integer.parseInt(mm13.getText()));
    query5.setParameter("v14",Integer.parseInt(mm14.getText()));
    query5.setParameter("v15",Integer.parseInt(mm15.getText()));
    query5.setParameter("v16",Integer.parseInt(mm16.getText()));
    query5.setParameter("v17",Integer.parseInt(mm17.getText()));
    query5.setParameter("v18",Integer.parseInt(mm18.getText()));
    query5.setParameter("v19",Integer.parseInt(mm19.getText()));
    query5.setParameter("v20",Integer.parseInt(mm20.getText()));
    query5.setParameter("v21",Integer.parseInt(mm21.getText()));
    query5.setParameter("v22",Integer.parseInt(mm22.getText()));
    query5.setParameter("v23",Integer.parseInt(mm23.getText()));
    query5.setParameter("v24",Integer.parseInt(mm24.getText()));
    query5.setParameter("v25",Integer.parseInt(mm25.getText()));
    query5.setParameter("v26",Integer.parseInt(mm26.getText()));
    query5.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query6=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=6");
    query6.setParameter("v1", Integer.parseInt(j1.getText()));
    query6.setParameter("v2", Integer.parseInt(j2.getText()));
    query6.setParameter("v3", Integer.parseInt(j3.getText()));
    query6.setParameter("v4", Integer.parseInt(j4.getText()));
    query6.setParameter("v5", Integer.parseInt(j5.getText()));
    query6.setParameter("v6", Integer.parseInt(j6.getText()));
    query6.setParameter("v7", Integer.parseInt(j7.getText()));
    query6.setParameter("v8", Integer.parseInt(j8.getText()));
    query6.setParameter("v9", Integer.parseInt(j9.getText()));
    query6.setParameter("v10",Integer.parseInt(j10.getText()));
    query6.setParameter("v11",Integer.parseInt(j11.getText()));
    query6.setParameter("v12",Integer.parseInt(j12.getText()));
    query6.setParameter("v13",Integer.parseInt(j13.getText()));
    query6.setParameter("v14",Integer.parseInt(j14.getText()));
    query6.setParameter("v15",Integer.parseInt(j15.getText()));
    query6.setParameter("v16",Integer.parseInt(j16.getText()));
    query6.setParameter("v17",Integer.parseInt(j17.getText()));
    query6.setParameter("v18",Integer.parseInt(j18.getText()));
    query6.setParameter("v19",Integer.parseInt(j19.getText()));
    query6.setParameter("v20",Integer.parseInt(j20.getText()));
    query6.setParameter("v21",Integer.parseInt(j21.getText()));
    query6.setParameter("v22",Integer.parseInt(j22.getText()));
    query6.setParameter("v23",Integer.parseInt(j23.getText()));
    query6.setParameter("v24",Integer.parseInt(j24.getText()));
    query6.setParameter("v25",Integer.parseInt(j25.getText()));
    query6.setParameter("v26",Integer.parseInt(j26.getText()));
    query6.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query7=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=7");
    query7.setParameter("v1", Integer.parseInt(jj1.getText()));
    query7.setParameter("v2", Integer.parseInt(jj2.getText()));
    query7.setParameter("v3", Integer.parseInt(jj3.getText()));
    query7.setParameter("v4", Integer.parseInt(jj4.getText()));
    query7.setParameter("v5", Integer.parseInt(jj5.getText()));
    query7.setParameter("v6", Integer.parseInt(jj6.getText()));
    query7.setParameter("v7", Integer.parseInt(jj7.getText()));
    query7.setParameter("v8", Integer.parseInt(jj8.getText()));
    query7.setParameter("v9", Integer.parseInt(jj9.getText()));
    query7.setParameter("v10",Integer.parseInt(jj10.getText()));
    query7.setParameter("v11",Integer.parseInt(jj11.getText()));
    query7.setParameter("v12",Integer.parseInt(jj12.getText()));
    query7.setParameter("v13",Integer.parseInt(jj13.getText()));
    query7.setParameter("v14",Integer.parseInt(jj14.getText()));
    query7.setParameter("v15",Integer.parseInt(jj15.getText()));
    query7.setParameter("v16",Integer.parseInt(jj16.getText()));
    query7.setParameter("v17",Integer.parseInt(jj17.getText()));
    query7.setParameter("v18",Integer.parseInt(jj18.getText()));
    query7.setParameter("v19",Integer.parseInt(jj19.getText()));
    query7.setParameter("v20",Integer.parseInt(jj20.getText()));
    query7.setParameter("v21",Integer.parseInt(jj21.getText()));
    query7.setParameter("v22",Integer.parseInt(jj22.getText()));
    query7.setParameter("v23",Integer.parseInt(jj23.getText()));
    query7.setParameter("v24",Integer.parseInt(jj24.getText()));
    query7.setParameter("v25",Integer.parseInt(jj25.getText()));
    query7.setParameter("v26",Integer.parseInt(jj26.getText()));
    query7.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query8=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=8");
    query8.setParameter("v1", Integer.parseInt(ag1.getText()));
    query8.setParameter("v2", Integer.parseInt(ag2.getText()));
    query8.setParameter("v3", Integer.parseInt(ag3.getText()));
    query8.setParameter("v4", Integer.parseInt(ag4.getText()));
    query8.setParameter("v5", Integer.parseInt(ag5.getText()));
    query8.setParameter("v6", Integer.parseInt(ag6.getText()));
    query8.setParameter("v7", Integer.parseInt(ag7.getText()));
    query8.setParameter("v8", Integer.parseInt(ag8.getText()));
    query8.setParameter("v9", Integer.parseInt(ag9.getText()));
    query8.setParameter("v10",Integer.parseInt(ag10.getText()));
    query8.setParameter("v11",Integer.parseInt(ag11.getText()));
    query8.setParameter("v12",Integer.parseInt(ag12.getText()));
    query8.setParameter("v13",Integer.parseInt(ag13.getText()));
    query8.setParameter("v14",Integer.parseInt(ag14.getText()));
    query8.setParameter("v15",Integer.parseInt(ag15.getText()));
    query8.setParameter("v16",Integer.parseInt(ag16.getText()));
    query8.setParameter("v17",Integer.parseInt(ag17.getText()));
    query8.setParameter("v18",Integer.parseInt(ag18.getText()));
    query8.setParameter("v19",Integer.parseInt(ag19.getText()));
    query8.setParameter("v20",Integer.parseInt(ag20.getText()));
    query8.setParameter("v21",Integer.parseInt(ag21.getText()));
    query8.setParameter("v22",Integer.parseInt(ag22.getText()));
    query8.setParameter("v23",Integer.parseInt(ag23.getText()));
    query8.setParameter("v24",Integer.parseInt(ag24.getText()));
    query8.setParameter("v25",Integer.parseInt(ag25.getText()));
    query8.setParameter("v26",Integer.parseInt(ag26.getText()));
    query8.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query9=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=9");
    query9.setParameter("v1", Integer.parseInt(s1.getText()));
    query9.setParameter("v2", Integer.parseInt(s2.getText()));
    query9.setParameter("v3", Integer.parseInt(s3.getText()));
    query9.setParameter("v4", Integer.parseInt(s4.getText()));
    query9.setParameter("v5", Integer.parseInt(s5.getText()));
    query9.setParameter("v6", Integer.parseInt(s6.getText()));
    query9.setParameter("v7", Integer.parseInt(s7.getText()));
    query9.setParameter("v8", Integer.parseInt(s8.getText()));
    query9.setParameter("v9", Integer.parseInt(s9.getText()));
    query9.setParameter("v10",Integer.parseInt(s10.getText()));
    query9.setParameter("v11",Integer.parseInt(s11.getText()));
    query9.setParameter("v12",Integer.parseInt(s12.getText()));
    query9.setParameter("v13",Integer.parseInt(s13.getText()));
    query9.setParameter("v14",Integer.parseInt(s14.getText()));
    query9.setParameter("v15",Integer.parseInt(s15.getText()));
    query9.setParameter("v16",Integer.parseInt(s16.getText()));
    query9.setParameter("v17",Integer.parseInt(s17.getText()));
    query9.setParameter("v18",Integer.parseInt(s18.getText()));
    query9.setParameter("v19",Integer.parseInt(s19.getText()));
    query9.setParameter("v20",Integer.parseInt(s20.getText()));
    query9.setParameter("v21",Integer.parseInt(s21.getText()));
    query9.setParameter("v22",Integer.parseInt(s22.getText()));
    query9.setParameter("v23",Integer.parseInt(s23.getText()));
    query9.setParameter("v24",Integer.parseInt(s24.getText()));
    query9.setParameter("v25",Integer.parseInt(s25.getText()));
    query9.setParameter("v26",Integer.parseInt(s26.getText()));
    query9.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query10=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=10");
    query10.setParameter("v1", Integer.parseInt(o1.getText()));
    query10.setParameter("v2", Integer.parseInt(o2.getText()));
    query10.setParameter("v3", Integer.parseInt(o3.getText()));
    query10.setParameter("v4", Integer.parseInt(o4.getText()));
    query10.setParameter("v5", Integer.parseInt(o5.getText()));
    query10.setParameter("v6", Integer.parseInt(o6.getText()));
    query10.setParameter("v7", Integer.parseInt(o7.getText()));
    query10.setParameter("v8", Integer.parseInt(o8.getText()));
    query10.setParameter("v9", Integer.parseInt(o9.getText()));
    query10.setParameter("v10",Integer.parseInt(o10.getText()));
    query10.setParameter("v11",Integer.parseInt(o11.getText()));
    query10.setParameter("v12",Integer.parseInt(o12.getText()));
    query10.setParameter("v13",Integer.parseInt(o13.getText()));
    query10.setParameter("v14",Integer.parseInt(o14.getText()));
    query10.setParameter("v15",Integer.parseInt(o15.getText()));
    query10.setParameter("v16",Integer.parseInt(o16.getText()));
    query10.setParameter("v17",Integer.parseInt(o17.getText()));
    query10.setParameter("v18",Integer.parseInt(o18.getText()));
    query10.setParameter("v19",Integer.parseInt(o19.getText()));
    query10.setParameter("v20",Integer.parseInt(o20.getText()));
    query10.setParameter("v21",Integer.parseInt(o21.getText()));
    query10.setParameter("v22",Integer.parseInt(o22.getText()));
    query10.setParameter("v23",Integer.parseInt(o23.getText()));
    query10.setParameter("v24",Integer.parseInt(o24.getText()));
    query10.setParameter("v25",Integer.parseInt(o25.getText()));
    query10.setParameter("v26",Integer.parseInt(o26.getText()));
    query10.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query11=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=11");
    query11.setParameter("v1", Integer.parseInt(n1.getText()));
    query11.setParameter("v2", Integer.parseInt(n2.getText()));
    query11.setParameter("v3", Integer.parseInt(n3.getText()));
    query11.setParameter("v4", Integer.parseInt(n4.getText()));
    query11.setParameter("v5", Integer.parseInt(n5.getText()));
    query11.setParameter("v6", Integer.parseInt(n6.getText()));
    query11.setParameter("v7", Integer.parseInt(n7.getText()));
    query11.setParameter("v8", Integer.parseInt(n8.getText()));
    query11.setParameter("v9", Integer.parseInt(n9.getText()));
    query11.setParameter("v10",Integer.parseInt(n10.getText()));
    query11.setParameter("v11",Integer.parseInt(n11.getText()));
    query11.setParameter("v12",Integer.parseInt(n12.getText()));
    query11.setParameter("v13",Integer.parseInt(n13.getText()));
    query11.setParameter("v14",Integer.parseInt(n14.getText()));
    query11.setParameter("v15",Integer.parseInt(n15.getText()));
    query11.setParameter("v16",Integer.parseInt(n16.getText()));
    query11.setParameter("v17",Integer.parseInt(n17.getText()));
    query11.setParameter("v18",Integer.parseInt(n18.getText()));
    query11.setParameter("v19",Integer.parseInt(n19.getText()));
    query11.setParameter("v20",Integer.parseInt(n20.getText()));
    query11.setParameter("v21",Integer.parseInt(n21.getText()));
    query11.setParameter("v22",Integer.parseInt(n22.getText()));
    query11.setParameter("v23",Integer.parseInt(n23.getText()));
    query11.setParameter("v24",Integer.parseInt(n24.getText()));
    query11.setParameter("v25",Integer.parseInt(n25.getText()));
    query11.setParameter("v26",Integer.parseInt(n26.getText()));
    query11.executeUpdate();
    s.getTransaction().commit();    

    
        s.getTransaction().begin();    
    Query query12=s.createQuery("    update Kardex set ia1=:v1,ia2=:v2,ca1=:v3,ca2=:v4,ea1=:v5,ea2=:v6,ea1r=:v7,ea1nr=:v8,ea2r=:v9,ea2nr=:v10,\n" +
"    pa1r=:v11,pa1nr=:v12,pa2r=:v13,pa2nr=:v14,ta1r=:v15,ta1nr=:v16,ta2r=:v17,ta2nr=:v18,xa1r=:v19,xa1nr=:v20,xa2r=:v21,\n" +
"    xa2nr=:v22,cca1=:v23,cca2=:v24,npa1=:v25,npa2=:v26 where idMes=12");
    query12.setParameter("v1", Integer.parseInt(d1.getText()));
    query12.setParameter("v2", Integer.parseInt(d2.getText()));
    query12.setParameter("v3", Integer.parseInt(d3.getText()));
    query12.setParameter("v4", Integer.parseInt(d4.getText()));
    query12.setParameter("v5", Integer.parseInt(d5.getText()));
    query12.setParameter("v6", Integer.parseInt(d6.getText()));
    query12.setParameter("v7", Integer.parseInt(d7.getText()));
    query12.setParameter("v8", Integer.parseInt(d8.getText()));
    query12.setParameter("v9", Integer.parseInt(d9.getText()));
    query12.setParameter("v10",Integer.parseInt(d10.getText()));
    query12.setParameter("v11",Integer.parseInt(d11.getText()));
    query12.setParameter("v12",Integer.parseInt(d12.getText()));
    query12.setParameter("v13",Integer.parseInt(d13.getText()));
    query12.setParameter("v14",Integer.parseInt(d14.getText()));
    query12.setParameter("v15",Integer.parseInt(d15.getText()));
    query12.setParameter("v16",Integer.parseInt(d16.getText()));
    query12.setParameter("v17",Integer.parseInt(d17.getText()));
    query12.setParameter("v18",Integer.parseInt(d18.getText()));
    query12.setParameter("v19",Integer.parseInt(d19.getText()));
    query12.setParameter("v20",Integer.parseInt(d20.getText()));
    query12.setParameter("v21",Integer.parseInt(d21.getText()));
    query12.setParameter("v22",Integer.parseInt(d22.getText()));
    query12.setParameter("v23",Integer.parseInt(d23.getText()));
    query12.setParameter("v24",Integer.parseInt(d24.getText()));
    query12.setParameter("v25",Integer.parseInt(d25.getText()));
    query12.setParameter("v26",Integer.parseInt(d26.getText()));
    query12.executeUpdate();
    s.getTransaction().commit();    
    

    
    try{         
Connection a = DriverManager.getConnection("jdbc:mysql://localhost:3306/Laboratorio","root","Sistemas");
CallableStatement proc = a.prepareCall(" call sumatorias()");
proc.execute();
                }catch(SQLException e){
         System.out.println(e);
      }
    
    s.close();
    s= NewHibernateUtil.getSessionFactory().openSession();
    listar();
    
    }

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listar();
        
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wwh.opt.models.vanilla;

/**
 *
 * @author wenhua
 */
@FunctionalInterface
public interface IBlackScholesPrice {
    double calc(double S, double X, double r, double D, double v, double T);
}

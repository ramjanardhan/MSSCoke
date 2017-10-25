/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.purge;


import com.mss.ediscv.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface PurgeService {
    public String purgeProcess(PurgeAction purgeAction) throws ServiceLocatorException;
}

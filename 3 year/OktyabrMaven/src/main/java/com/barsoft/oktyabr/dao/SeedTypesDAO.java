/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.oktyabr.dao;

import com.barsoft.oktyabr.entities.SeedTypes;
import java.util.List;

/**
 *
 * @author maks
 */
public interface SeedTypesDAO {
    SeedTypesDAO INSTANCE_SEED_TYPES= new SeedTypesDAOImpl();
    List<SeedTypes> getSeedTypes();
    void addSeedType(SeedTypes seedType);
    //и метод с которым мы будем работать
}
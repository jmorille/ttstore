package com.cestpasdur.service;

import com.cestpasdur.domain.Groupe;
import com.cestpasdur.domain.Personne;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Copyright (C) 2010 Damien GOUYETTE <damien.gouyette@gmail.com>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@Service
@RemotingDestination(value = "groupeService", channels = "my-amf")
@Transactional
public class GroupeServiceImpl {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }


    public void add(Groupe groupe) {
        em.persist(groupe);
    }

    @RemotingInclude
    public void update(Groupe groupe) {
        em.merge(groupe);
    }

    @RemotingInclude
    public void remove(Groupe groupe) {
        Groupe gr = em.merge(groupe);
        em.remove(gr);
    }

    @RemotingInclude
    public Collection<Groupe> getAll() {
        Query q = em.createQuery("Select g from Groupe g");
        return q.getResultList();
    }

    
}

/*
 * Copyright 2016 by floragunn UG (haftungsbeschränkt) - All rights reserved
 * 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed here is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * This software is free of charge for non-commercial and academic use. 
 * For commercial use in a production environment you have to obtain a license 
 * from https://floragunn.com
 * 
 */

package com.floragunn.searchguard.dlic.auditlog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.common.inject.Provider;
import org.elasticsearch.common.settings.Settings;

import com.floragunn.searchguard.auditlog.impl.AbstractAuditLog;
import com.floragunn.searchguard.auditlog.impl.AuditMessage;

public class TestAuditlogImpl extends AbstractAuditLog {

    public static List<AuditMessage> messages = new ArrayList<AuditMessage>(100);
    public static StringBuffer sb = new StringBuffer();
    
    public TestAuditlogImpl(Settings settings, IndexNameExpressionResolver resolver, Provider<ClusterService> clusterService) {
        super(settings, resolver, clusterService);
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    protected synchronized void save(AuditMessage msg) {
        sb.append(msg.toPrettyString()+System.lineSeparator());
        messages.add(msg);
    }
    
    public static synchronized void clear() {
        sb.setLength(0);
        messages.clear();
    }

}

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.hc.client5.http.impl.auth;

import org.apache.hc.client5.http.auth.AuthChallenge;
import org.apache.hc.client5.http.auth.AuthScheme;
import org.apache.hc.client5.http.auth.ChallengeType;
import org.apache.hc.client5.http.auth.StandardAuthScheme;

import org.junit.Assert;
import org.junit.Test;


public class TestNTLMScheme {

    @Test
    public void testNTLMAuthenticationEmptyProxyChallenge() throws Exception {
        final AuthChallenge authChallenge = new AuthChallenge(ChallengeType.PROXY, StandardAuthScheme.NTLM);
        final AuthScheme authScheme = new NTLMScheme();
        authScheme.processChallenge(authChallenge, null);

        Assert.assertFalse(
                "Challenge with empty value received from NTML proxy must not interrupt authentication process.",
                authScheme.isChallengeComplete());
        Assert.assertTrue(
                "Challenge with empty value received from NTML proxy must transit status of NTLMScheme to CHALLENGE_RECEIVED.",
                authScheme.toString().contains(NTLMScheme.State.CHALLENGE_RECEIVED.toString()));
    }
}
INSERT INTO oauth_client_details
(client_id, client_secret, resource_ids, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES
  (
    'client', 'secret',
             'sample-resource',
             'read,write',
             'password,authorization_code,refresh_token,client_credentials',
             NULL, 'ROLE_USER', 36000, 36000, NULL, TRUE
  ),
  (
    'admin', 'secret',
              'sample-resource',
              'read,write',
              'refresh_token,client_credentials',
              NULL, 'ROLE_ADMIN', 36000, 36000, NULL, TRUE

  );

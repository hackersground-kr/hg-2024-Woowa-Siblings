param location string = resourceGroup().location

param teamName string

@secure()
param username string

@secure()
param password string

resource containerRegistry 'Microsoft.ContainerRegistry/registries@2023-07-01' = {
  name: teamName
  location: location
  sku: {
    name: 'Basic'
  }
  properties: {
    adminUserEnabled: true
  }
}

resource mySQLdb 'Microsoft.DBforMySQL/flexibleServers@2023-12-30' = {
  name: '${teamName}-db'
  location: location
  properties: {
    administratorLogin: username
    administratorLoginPassword: containerRegistry.listCredentials().passwords[0].value
    createMode: 'Default'
  }
}

resource firewall 'Microsoft.DBforMySQL/flexibleServers/firewallRules@2023-12-30' = {
  name: 'firewallrules'
  parent: mySQLdb
  properties: {
    endIpAddress: '255.255.255.255'
    startIpAddress: '0.0.0.0'
  }
}

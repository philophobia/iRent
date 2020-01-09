import Axios from 'axios'

Axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

export default {
    enter: function ({ account, password }) {
        return Axios.request('/api/login?id=' + account + "&password=" + password);
    },
    exit: function() {
        return Axios.request('/api/logout');
    },
    getAllUsers: function () {
        return Axios.get('/api/users');
    },
    searchUser: function ({ uid }) {
        return Axios.get('/api/users?id=' + uid);
    },
    getAllHouses: function () {
        return Axios.get('/api/houses');
    },
    searchHouse: function ({ hid }) {
        return Axios.get('/api/houses?id=' + hid);
    },
    getAllReports: function () {
        return Axios.get('/api/reports');
    },
    searchReportById: function ({ rid }) {
        return Axios.get('/api/reports?id='+ rid);
    },
    searchReportByStatus: function ({ stat }) {
        return Axios.get('/api/reports?status='+ stat);
    },
    getAllLogs: function () {
        return Axios.get('/api/logs');
    },
    searchLogById: function ({ lid }) {
        return Axios.get('/api/logs?id=' + lid);
    },
    searchLogByTime: function ({ start, end }) {
        return Axios.get('/api/logs?start=' + start + "&end=" + end);
    },
    /*updateUser: function({ uid, operation}){
        return Axios.put('/api/users?id=' + uid + "&operation=" + operation);
    },*/
    updateReport: function({ rid, stat }) {
        return Axios.put('/api/reports?id=' + rid + "&status=" + stat);
    },
    deleteUser: function({ uid }) {
        return Axios.delete('/api/users?id=' + uid);
    },
    deleteHouse: function({ hid }) {
        return Axios.delete('/api/houses?id=' + hid);
    }
}
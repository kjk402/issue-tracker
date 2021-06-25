// useFetch 커스텀훅으로 수정예정

const END_POINT = "http://ec2-13-124-158-166.ap-northeast-2.compute.amazonaws.com/api"

const TOKEN = JSON.parse(localStorage.getItem("issue-tracker-user"));

const headerMaker = ({token}) => {
  return {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `bearer ${token}`
    },
  }
}
const bodyMaker = (data) => {
  return {
    method: 'POST',
    body: JSON.stringify(data),
  }
}

const API = {
  get: {
    issues: async (query) => {
      let targetURL = `${END_POINT}/issues`;
      if (query) targetURL += `?searchFilter=${query}`;
      return await fetch(targetURL, {...headerMaker({token: TOKEN.accessToken})}).then((response) => {
        return response.json();
      });
      // console.log("API call", result);
    },
    users: async () => {
      return await fetch(`${END_POINT}/users`, {...headerMaker({token: TOKEN.accessToken})}).then((response) => {
        return response.json();
      });
    },
    labels: async () => {
      const result = await fetch(`${END_POINT}/labels`, {...headerMaker({token: TOKEN.accessToken})}).then((response) => {
        return response.json();
      });
      return result.labels;
    },
    milestones: async () => {
      const result = await fetch(`${END_POINT}/milestones`, {...headerMaker({token: TOKEN.accessToken})}).then((response) => {
        return response.json();
      });
      return result.milestones;
    },
    issuesDetail: async (id) => {
      const result = await fetch(`${END_POINT}/issues/detail?issueId=${id}`, 
        {...headerMaker({token: TOKEN.accessToken})}
      ).then((response) => {
        return response.json();
      });
      return result;
    }
  },
  post: {
    issues: async (data) => {
      const result = await fetch(`${END_POINT}/issues`, 
        {
          ...headerMaker({token: TOKEN.accessToken}),
          ...bodyMaker(data)
        }).then((response) => {
          return response;
        });
      return result;
    }
  },
  patch: {},
  put: {},
  delete: {}
}

export default API;
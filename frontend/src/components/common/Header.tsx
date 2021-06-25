import { useState, useEffect} from 'react';
import styled from 'styled-components';
import { Link, withRouter } from 'react-router-dom';

import ResponsiveLayout from 'components/common/ResponsiveLayout';

const Header = () => {

  const [userInfo, setUserInfo] = useState<any>();
  useEffect(() => {
    if (localStorage.getItem("issue-tracker-user")) {
      const parsedIssueTrackerUser = JSON.parse(localStorage.getItem("issue-tracker-user") as string);
      setUserInfo(parsedIssueTrackerUser);
    } else {
      setUserInfo({});
    }
  }, [])

  const handleHeaderClick = () => {
    window.location.href = "/issues";
  }

  if (!userInfo) return <></>;
  
  return (
    <HeaderLayout>
      <HeaderTitle onClick={handleHeaderClick}>Issue Tracker</HeaderTitle>
      {userInfo.profileImage && 
        <HeaderThumbnail src={userInfo.profileImage}></HeaderThumbnail>
      }
    </HeaderLayout>
  )
}

const HeaderLayout = styled(ResponsiveLayout)`
  padding-top: 27px;
  padding-bottom: 27px;
  background-color: #F7F7FC;

  display: flex;
  justify-content: space-between;
`;
const HeaderTitle = styled.span`
  font-weight: 500; 
  font-style: italic;
  font-size: 3.2rem;
  cursor: pointer;
`;

const HeaderThumbnail = styled.img`
  width: 36px;
  border-radius: 50%;
  
`;
export default Header;
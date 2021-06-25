import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

import Dropdown from 'components/common/Dropdown';
import Label from 'components/common/Label';

import FilterData from './FilterData';

import API from 'lib/API/API';

const IssueList = () => {
  const [issues, setIssues] = useState<any>([]);
  const [filterInfo, setFilterInfo] = useState<any>({});

  useEffect(() => {
    const fetchIssues = async () => {
      const result = await API.get.issues("is:open");
      // console.log("fetchIssues", result);
      setIssues(result.issueList);
    }
    fetchIssues();

    const createFilterData = async () => {
      const result = FilterData;
      // console.log("fetchFilterMockData", result);
      result["IssueManagerFilterInfo"].elements = await API.get.users();
      result["IssueLabelFilterInfo"].elements = await API.get.labels();
      result["IssueMilestoneFilterInfo"].elements = await API.get.milestones()
      result["IssueAuthorFilterInfo"].elements = await API.get.users();
      
      console.log('createFilterData', result)
      setFilterInfo(result);
    }
    createFilterData();
    
  }, []);

  if (issues.length === 0 || Object.keys(filterInfo).length === 0) return <></>;
  return (
    <IssueListLayout>
      <IssueListHeader>
        <IssueListCheckBoxLayer>
          <input type="checkbox" />
        </IssueListCheckBoxLayer>
        <IssueListButtonGroupLayer>
          <button> 열린 이슈 </button>
          <button> 닫힌 이슈 </button>
        </IssueListButtonGroupLayer>
        <IssueListFilterGroupLayer>
          <Dropdown info={filterInfo["IssueManagerFilterInfo"]} />
          <Dropdown info={filterInfo["IssueLabelFilterInfo"]} />
          <Dropdown info={filterInfo["IssueMilestoneFilterInfo"]} />
          <Dropdown info={filterInfo["IssueAuthorFilterInfo"]} />
        </IssueListFilterGroupLayer>
      </IssueListHeader>

      {issues.map((issue, i) => {
        return (
          <Link to={`/issue/${issue.issueDTO.issueInfo.issueId}`} >
            <IssueListContents key={`issue-${i}`}>
              <IssueListCheckBoxLayer>
                <input type="checkbox" />
              </IssueListCheckBoxLayer>
              <IssueListDetailInfomationLayer>
                <DetailInformationTitleArea>
                  <DetailInformationTitle> {issue.issueDTO.issueInfo.title} </DetailInformationTitle>
                  <Label type={"DEFAULT"} value={"레이블 이름"} />
                </DetailInformationTitleArea>
                <DetailInformationContents>#{issue.issueDTO.issueInfo.issueId} </DetailInformationContents>
              </IssueListDetailInfomationLayer>
              <IssueListProfileLayer>
                <ProfileImg src={issue.issueDTO.userDTO.profileImage} />
              </IssueListProfileLayer>
            </IssueListContents>
          </Link>
        )
      })}
    </IssueListLayout>
  )
}

const IssueListLayout = styled.div`
  width: 100%;
  border: 1px solid #D9DBE9;
  border-radius: 16px;

  display: flex;
  flex-direction: column;
  
  & > div:last-child {
    border-radius: 0 0 16px 16px;
  }
`;

const IssueListHeader = styled.div`
  width: 100%;
  background: #F7F7FC;
  
  padding: 16px 32px;
  border-radius: 16px 16px 0px 0px;
  border-bottom: 1px solid #D9DBE9;
  box-sizing: border-box;

  display: flex;
`;

const IssueListContents = styled.div`
  width: 100%;
  background-color: white;
  
  padding: 16px 32px;
  box-sizing: border-box;
  
  display: flex;

  & + & {
    border-top: 1px solid #D9DBE9;
  } 
  
`;

const IssueListLayer = styled.div`
  display: flex;
`;
const IssueListCheckBoxLayer = styled(IssueListLayer)`
  margin-right: 30px;
  align-items: center;
`;
const IssueListButtonGroupLayer = styled(IssueListLayer)`
  
`;
const IssueListFilterGroupLayer = styled(IssueListLayer)`
  margin-left: auto;
`;

const IssueListDetailInfomationLayer = styled(IssueListLayer)`
  flex-direction: column;
  width: 100%;
`

const DetailInformationTitle = styled.span`
  font-weight: 700;
  font-size: 1.89rem;
  margin-right: 10px;
`;

const DetailInformationTitleArea = styled(IssueListLayer)`
  margin-top: 10px;
`

const DetailInformationContents = styled(IssueListLayer)`
  margin-top: 10px;
`;

const IssueListProfileLayer = styled(IssueListLayer)`
  margin-left: auto;
`;

const ProfileImg = styled.img`
  width: 50px;
  height: 50px;
  border-radius: 50%;
`;

export default IssueList;
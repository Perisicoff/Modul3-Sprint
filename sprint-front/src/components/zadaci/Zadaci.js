import React, { useCallback, useEffect, useState } from 'react'
import { Button, Col, Collapse, Form, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import Axios from '../../apis/Axios';
import {IsLoggedIn} from '../../service/auth'
import '../../index.css'
import { Rola } from '../../service/auth'

const Zadaci = (props) => {

    var navigate = useNavigate()
    const [zadaci, setZadaci] = useState([])
    const [sprintevi, setSprintevi] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [isChecked, setIsChecked] = useState(false);
    const [searchParams, setSearchParams] = useState({
      ime: "",
      sprintId: ""
    })
  
    const getAll = useCallback((nextPage) => {
        const config = {
          params: {
            ime: searchParams.ime,
            sprintId: searchParams.sprintId,
            pageNo: nextPage
          }
        }
        Axios.get('/zadaci', config)
        .then(res => {
          console.log(res)
          setZadaci(res.data)
          setPageNo(nextPage)
          setTotalPage(res.headers["total-pages"])
        })
        .catch(err => {
          console.log(err)
        })
    }, [])

    const getAll2 = useCallback(() => {
      Axios.get('/sprintevi')
      .then(res => {
        console.log(res)
        setSprintevi(res.data)
      })
      .catch(err => {
        console.log(err)
      })
    }, [])
  
    useEffect(() => {
      getAll(pageNo)
      getAll2()
    }, [])

    const goToEdit = (zadatak) => {
      navigate('/zadatak/edit')
      props.callBackF(zadatak)
    }

    const goToAdd = () => {
      navigate('/zadatak/add')
    }

    const delte = (zadatakId) => {
      const confirmDelete = window.confirm("Da li ste sigurni da zelite da obrisete ovaj film?");
      if (confirmDelete) {
        Axios.delete('/zadaci/' + zadatakId)
        .then(res => {
          console.log(res)
          setZadaci((zadaci)=>zadaci.filter(zadatak => zadatak.id !== zadatakId))
        })
        .catch(err => {
          console.log(err)
        })
      } else {
        navigate('/zadaci')
      }
    }

    const novoStanje = (zadatakId) => {
        const potvrdaStanja = window.confirm("Da li ste sigurni da zelite da promenite stanje?");
        if (potvrdaStanja) {
          Axios.put('/zadaci/' + zadatakId + '/novoStanje')
          .then(res => {
            console.log(res)
            window.location.reload()
          })
          .catch(err => {
            console.log(err)
            alert("neuspesna promena!")
          })
        } else {
          navigate('/zadaci')
        }
    }

    const rednerAll = () => {
      return zadaci.map((zadatak) => {
        return(
          <tr key={zadatak.id}>
            <td>{zadatak.ime}</td>
            <td>{zadatak.zaduzeni}</td>
            <td>{zadatak.bodovi}</td>
            <td>{zadatak.nazivSprinta}</td>
            <td>{zadatak.nazivStanje}</td>
            {IsLoggedIn() && <td><Button disabled={zadatak.stanjeId === 3} className="btn btn-info btn-sm" onClick={() => novoStanje(zadatak.id)}>Predji na sledece stanje</Button></td>}
            {IsLoggedIn() && Rola() == "admin" && <td><Button className="btn btn-warning btn-sm" onClick={() => goToEdit(zadatak)}>Izmeni</Button></td>}
            {IsLoggedIn() && Rola() == "admin" && <td><Button className="btn btn-danger btn-sm" onClick={() => delte(zadatak.id)}>Ukloni</Button></td>}
          </tr>
        )
      })

    }

    const rednerAll2 = () => {
      return sprintevi.map((sprint) => {
        return(<option key={sprint.id} value={sprint.id}>{sprint.ime}</option>)
      })

    }

    const serchValue = (event) => {
      let name = event.target.name
      let value = event.target.value
      
      searchParams[name] = value
      setSearchParams(searchParams)
      getAll(0)
    }
    
    return (
      <div>
        <Row className="justify-content-center">
        <Col  xs="12" sm="10" md="8">
          <br/><br/>
          <Row><h1>Zadaci</h1></Row>
            <Form.Group style={{marginTop:35}}>
                <Form.Check type="checkbox" label="Show search form" onClick={(event) => setIsChecked(event.target.checked)}/>
            </Form.Group>
            <Collapse in={isChecked}>
            <Form style={{marginTop:10}}>
                <Form.Group>
                    <Form.Label>Ime zadatka</Form.Label>
                    <Form.Control type='text' name='ime' onChange={serchValue} ></Form.Control><br/>
                </Form.Group>
                <Form.Group>
                    <Form.Select name='sprintId' onChange={serchValue}>
                        <option value={""}>--izaberi sprint--</option>
                        {rednerAll2()}
                    </Form.Select>
                </Form.Group>
            </Form>
            </Collapse>
          <Row>
            <Col>
              <Table>
                <thead>
                  <tr>
                    <th>Ime</th>
                    <th>Zaduzeni</th>
                    <th>Bodovi</th>
                    <th>Sprint</th>
                    <th>Stanje</th>
                    <th>Akcija</th>
                    <th></th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  {rednerAll()}
                </tbody>
              </Table>
            </Col>
          </Row>
          <Row>{IsLoggedIn() && Rola() == "admin" && <Button className="btn btn-primary btn-sm" onClick={() => goToAdd()}>Novi zadatak</Button>}</Row>
          <br/>
          <div style={{ display: 'flex', justifyContent: 'center' }}>
            <Button className="btn btn-light btn-sm" disabled={pageNo==0} onClick={() => getAll(pageNo-1)}>{'❮'}</Button>
            <span style={{ margin: '10px' }}> {pageNo + 1} </span> 
           <Button className="btn btn-light btn-sm" disabled={pageNo==totalPage-1 || zadaci.length === 0} onClick={() => getAll(pageNo+1)}>{'❯'}</Button>
          </div>
        </Col>
        </Row>
      </div>
    );
  }

export default Zadaci;
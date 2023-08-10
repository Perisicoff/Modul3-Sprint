import { useCallback, useEffect, useState } from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";

const NoviZadatak = () => {

    const zadatak = {
        id: -1,
        ime: '',
        zaduzeni: '',
        bodovi: 0,
        sprintId: -1,
        stanjeId: -1

    }
    const [sprintevi, setSprintevi] = useState([])
    const [stanja, setStanja] = useState([])
    const [noviZadatak, setNoviZadatak] = useState(zadatak)
    var navigate = useNavigate()


    const dodaj = () => {

        const dto = {
            ime: noviZadatak.ime,
            zaduzeni: noviZadatak.zaduzeni,
            bodovi: noviZadatak.bodovi,
            sprintId: noviZadatak.sprintId,
            stanjeId: noviZadatak.stanjeId
        }

        Axios.post('/zadaci', dto)
            .then(res => {
                console.log(res)
                navigate('/zadaci')
            })
            .catch(err => {
                console.log(err)
                alert("Doslo je do greske, pokusajte novi unos!")
            })
    }

    const getAll2 = useCallback(() => {
        Axios.get('/stanja')
        .then(res => {
          console.log(res)
          setSprintevi(res.data)
        })
        .catch(err => {
          console.log(err)
        })
    }, [])

    const getAll3 = useCallback(() => {
        Axios.get('/sprintevi')
        .then(res => {
          console.log(res)
          setStanja(res.data)
        })
        .catch(err => {
          console.log(err)
        })
    }, [])

    useEffect(() => {
        getAll2()
        getAll3()
      }, [])

    const valueInputChanged = (e) => {

        let input = e.target;
        let name = input.name;
        let value = input.value;

        noviZadatak[name] = value;
        setNoviZadatak(noviZadatak);
    }

    const rednerAll2 = () => {
        return sprintevi.map((sprint) => {
        return(<option key={sprint.id} value={sprint.id}>{sprint.ime}</option>)
        })

    }

    const rednerAll3 = () => {
        return stanja.map((stanje) => {
        return(<option key={stanje.id} value={stanje.id}>{stanje.ime}</option>)
        })

    }

    return(
        <div>
            <Col>
          <br/><br/>
          <Row><h1>Novi zadatak</h1></Row>
          <br/>
          <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
              <Form>
                <Form.Label htmlFor="ime">Ime</Form.Label>
                <Form.Control id="ime" type="text" name="ime" onChange={(e) => valueInputChanged(e)}/>
                <Form.Label htmlFor="zaduzeni">Zaduzeni</Form.Label>
                <Form.Control id="zaduzeni" name="zaduzeni" type="text" onChange={(e) => valueInputChanged(e)}/>
                <Form.Label htmlFor="bodovi">Bodovi</Form.Label>
                <Form.Control id="bodovi" name="bodovi" type="number" onChange={(e) => valueInputChanged(e)}/>
                <Form.Label htmlFor="stanjeId">Stanje</Form.Label>
                <Form.Select name='stanjeId' onChange={(e) => valueInputChanged(e)}>
                        <option value={""}>--izaberi stanje--</option>
                        {rednerAll3()}
                </Form.Select>
                <Form.Label htmlFor="sprintId">Sprint</Form.Label>
                <Form.Select name='sprintId' onChange={(e) => valueInputChanged(e)}>
                        <option value={""}>--izaberi sprint--</option>
                        {rednerAll2()}
                    </Form.Select>
                <br/>
                <Button className="btn btn-primary" onClick={() => dodaj()}>Dodaj zadatak</Button>
              </Form>
            </Col>
            <Col></Col>
          </Row>
        </Col>
        </div>
    );


}

export default NoviZadatak;